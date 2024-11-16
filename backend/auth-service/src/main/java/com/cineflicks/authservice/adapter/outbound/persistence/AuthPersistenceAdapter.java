package com.cineflicks.authservice.adapter.outbound.persistence;

import com.cineflicks.authservice.adapter.outbound.persistence.entity.TokenEntity;
import com.cineflicks.authservice.adapter.outbound.persistence.mapper.AuthPersistenceMapper;
import com.cineflicks.authservice.adapter.outbound.persistence.repository.TokenRepository;
import com.cineflicks.authservice.application.ports.outbound.persistence.AuthPersistencePort;
import com.cineflicks.authservice.domain.model.Token;
import com.cineflicks.authservice.domain.model.UserDTO;
import com.cineflicks.authservice.infrastructure.security.jwt.JwtService;
import com.cineflicks.authservice.utils.EmailTemplateName;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.springframework.mail.javamail.MimeMessageHelper.MULTIPART_MODE_MIXED;

@Component
@RequiredArgsConstructor
public class AuthPersistenceAdapter implements AuthPersistencePort {

    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    @Value("${application.mailing.frontend.activation-url}")
    private String activationUrl;

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final AuthPersistenceMapper persistenceMapper;
    private final TokenRepository tokenRepository;



    @Override
    public String login(UserDTO user) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getEmail(),
                        user.getPassword()));
        return jwtService.generateToken(user.getEmail());
    }


    @Override
    public void sendValidationEmail(UserDTO user) {
        var newToken = generateAndActivationToken(user);
        try {
            sendEmail(
                    user.getEmail(),
                    user.fullName(),
                    activationUrl,
                    newToken
            );
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateAndActivationToken(UserDTO user) {
        String generatedToken = generateActivationCode(6);
        tokenRepository.save(TokenEntity.builder()
                .token(generatedToken)
                .createdAt(LocalDateTime.now())
                .expiredAt(LocalDateTime.now().plusMinutes(15))
                .userId(user.getId())
                .build());
        return generatedToken;
    }

    private String generateActivationCode(int length) {
        String characters = "0123456789";
        StringBuilder codeBuilder = new StringBuilder();
        SecureRandom secureRandom = new SecureRandom();
        for (int i = 0; i <length ; i++) {
            int randomIndex = secureRandom.nextInt(characters.length());
            codeBuilder.append(characters.charAt(randomIndex));
        }
        return codeBuilder.toString();
    }


    @Async
    private void sendEmail(
            String to,
            String fullName,
            String confirmationEmail,
            String activationCode) throws MessagingException {
        String templateName;
        templateName = EmailTemplateName.ACTIVATE_ACCOUNT.name();

        MimeMessage mimeMessage = mailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(
                    mimeMessage,
                    MULTIPART_MODE_MIXED,
                    UTF_8.name());
            Map<String, Object> properties = new HashMap<>();
            properties.put("fullName", fullName);
            properties.put("confirmationUrl", confirmationEmail);
            properties.put("activation_code", activationCode);

            Context context = new Context();
            context.setVariables(properties);

            helper.setFrom("engineers.newgeneration@gmail.com");
            helper.setTo(to);
            helper.setSubject("Account activation");

            String template = templateEngine.process(templateName, context);

            helper.setText(template, true);
            mailSender.send(mimeMessage);

        }
    @Override
    public Token validateTokenExists(String token) {
        return persistenceMapper.toToken(tokenRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid token: the provided token does not match any records.")));
    }


    @Override
    public boolean isTokenExpired(Token token) {
        return LocalDateTime.now().isAfter(token.getExpiredAt());
    }


    @Transactional
    @Override
    public void validateToken(Token token) {
        token.setValidatedAt(LocalDateTime.now());
        tokenRepository.save(persistenceMapper.toTokenEntity(token));
    }

}
