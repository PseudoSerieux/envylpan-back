package com.example.envyplan.util;

import io.smallrye.jwt.build.Jwt;
import io.smallrye.jwt.build.JwtClaimsBuilder;
import io.smallrye.jwt.auth.principal.JWTParser;
import io.smallrye.jwt.auth.principal.ParseException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.jwt.Claims;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;

import java.time.Instant;
import java.util.Date;

@ApplicationScoped
public class JwtUtil {

    @ConfigProperty(name = "jwt.secret")
    String secretKey;

    @ConfigProperty(name = "jwt.expiration")
    long expiration;

    @Inject
    JWTParser jwtParser;

    @Inject
    JsonWebToken jwt;

    @Inject
    Logger log;

    public String generateToken(String username) {
        Instant now = Instant.now();
        JwtClaimsBuilder claims = Jwt.claims()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiration / 1000))
                .subject(username);

        return claims.sign();
    }

    public String getUsernameFromToken(String token) {
        try {
            return jwtParser.parse(token).getSubject();
        } catch (ParseException e) {
            log.error("Invalid JWT token", e);
            throw new RuntimeException("Invalid JWT token", e);
        }
    }

    public boolean validateToken(String token, String username) {
        try {
            String tokenUsername = getUsernameFromToken(token);
            return tokenUsername.equals(username) && !isTokenExpired(token);
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isTokenExpired(String token) {
        try {
            Long expirationDate = Date.from(jwtParser.parse(token).getExpirationTime());
            return expirationDate.before(new Date());
        } catch (ParseException e) {
            log.error("Invalid JWT token", e);
            return true;
        }
    }

    public String getCurrentUsername() {
        if (jwt != null && jwt.getSubject() != null) {
            return jwt.getSubject();
        }
        return null;
    }
}