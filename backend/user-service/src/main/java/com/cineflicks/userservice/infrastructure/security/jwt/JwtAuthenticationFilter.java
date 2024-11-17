package com.cineflicks.userservice.infrastructure.security.jwt;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;



@Service
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        if (request.getServletPath().contains("/api/v1/user/save")
                || request.getServletPath().contains("/api/v1/user/getUserById/{id}")
                || request.getServletPath().contains("/api/v1/user/getUserByEmail/{email}")
                || request.getServletPath().contains("/api/v1/user/enable")){
            filterChain.doFilter(request, response);
            return;
        }
        try {
            String token = request.getHeader("Authorization");
            if (StringUtils.hasText(token) && token.startsWith("Bearer ")) {
                token = token.substring(7).trim();
                System.out.println("Token to Verify: '" + token + "'");
                Claims claims = jwtUtil.extractAllClaims(token);

            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(claims.getIssuer());
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        claims.getSubject(), null, Collections.singleton(authority));
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        filterChain.doFilter(request, response);
    }
}