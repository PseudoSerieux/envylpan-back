package com.example.envyplan.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import java.io.IOException;
import java.util.Date;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Value("${jwt.secret}") // Assurez-vous que vous avez configuré la clé secrète dans votre fichier application.properties
    private String secretKey;

    @Value("${jwt.expiration}") // Durée d'expiration du token (en millisecondes). Par exemple : 86400000 pour 1 jour.
    private long expiration;


    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, String secretKey, long expiration) {
        super(authenticationManager);
        this.secretKey = secretKey;
        this.expiration = expiration;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String username = ((org.springframework.security.core.userdetails.User) authResult.getPrincipal()).getUsername();
        String token = generateToken(username);

        response.addHeader("Authorization", "Bearer " + token);
    }

    private String generateToken(String username) {
        long expirationTime = System.currentTimeMillis() + expiration;

        return Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(expirationTime))
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }
}