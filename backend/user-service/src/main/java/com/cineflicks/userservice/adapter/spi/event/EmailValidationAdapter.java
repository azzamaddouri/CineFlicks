package com.cineflicks.userservice.adapter.spi.event;

import com.cineflicks.userservice.adapter.spi.persistence.mapper.TokenPersistenceMapper;
import com.cineflicks.userservice.adapter.spi.persistence.mapper.UserPersistenceMapper;
import com.cineflicks.userservice.adapter.spi.persistence.repository.TokenRepository;
import com.cineflicks.userservice.application.ports.spi.event.EmailValidationPort;
import com.cineflicks.userservice.domain.model.Token;
import com.cineflicks.userservice.domain.model.User;
import com.cineflicks.userservice.utils.EmailTemplateName;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
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
public class EmailValidationAdapter implements EmailValidationPort {

    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;
    private final UserPersistenceMapper userPersistenceMapper;
    private final TokenPersistenceMapper tokenPersistenceMapper;
    private final TokenRepository tokenRepository;
    @Value("${application.mailing.frontend.activation-url}")
    private String activationUrl;

    @Override
    public void sendValidationEmail(User user) {
        var newToken = generateAndActivationToken(user);
        var userEntity = userPersistenceMapper.toUserEntity(user);
        sendEmail(
                userEntity.getEmail(),
                userEntity.fullName(),
                EmailTemplateName.ACTIVATE_ACCOUNT,
                activationUrl,
                newToken,
                "Account activation"
        );
    }

    private String generateAndActivationToken(User user) {
        String generatedToken = generateActivationCode(6);
        var token = Token.builder()
                .token(generatedToken)
                .createdAt(LocalDateTime.now())
                .expiredAt(LocalDateTime.now().plusMinutes(15))
                .user(user)
                .build();
        tokenRepository.save(tokenPersistenceMapper.toTokenEntity(token));
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
            String username,
            EmailTemplateName emailTemplate,
            String confirmationEmail,
            String activationCode,
            String subject) {
        String templateName;
        if (emailTemplate == null) {
            templateName = "confirm-email";
        } else {
            templateName = emailTemplate.name();
        }

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(
                    mimeMessage,
                    MULTIPART_MODE_MIXED,
                    UTF_8.name());
            Map<String, Object> properties = new HashMap<>();
            properties.put("username", username);
            properties.put("confirmationUrl", confirmationEmail);
            properties.put("activation_code", activationCode);

            Context context = new Context();
            context.setVariables(properties);

            helper.setFrom("engineers.newgeneration@gmail.com");
            helper.setTo(to);
            helper.setSubject(subject);

            String template = templateEngine.process(templateName, context);

            helper.setText(template, true);
            mailSender.send(mimeMessage);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }
}
