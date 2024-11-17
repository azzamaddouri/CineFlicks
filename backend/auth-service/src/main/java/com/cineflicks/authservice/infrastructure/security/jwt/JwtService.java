package com.cineflicks.authservice.infrastructure.security.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class JwtService {

   @Value("${application.security.jwt.secret-key}")
    private String secretKey;
    @Value("${application.security.jwt.expiration}")
    private long jwtExpiration;

    private final UserDetailsServiceImpl userDetailsService;


    public String generateToken(String username) {
        return createToken(new HashMap<>(),userDetailsService.loadUserByUsername(username));
    }

    private String createToken(
            Map<String, Object> claims,
            UserDetails userDetails) {
        var authorities = userDetails.getAuthorities()
                .stream().
                map(GrantedAuthority::getAuthority)
                .toList();
        return Jwts.builder()
                .setClaims(claims)
                .setIssuer(userDetails.getAuthorities().iterator().next().getAuthority())
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .claim("authorities", authorities)
                .signWith(getSignInKey(), SignatureAlgorithm.HS256).compact();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        System.out.println("Key Length (bits): " + (keyBytes.length * 8));
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
