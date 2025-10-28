package com.example.envyplan.configuration;

import com.example.envyplan.service.AuthService;
import com.example.envyplan.util.JwtUtil;
import io.quarkus.arc.config.ConfigProperties;
import io.quarkus.security.Authenticated;
import io.quarkus.security.runtime.QuarkusSecurityIdentity;
import io.smallrye.jwt.build.Jwt;
import io.smallrye.jwt.auth.principal.JWTParser;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.resteasy.reactive.server.ServerRequestFilter;

import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.SecurityContext;
import jakarta.ws.rs.ext.Provider;
import java.security.Key;
import java.util.HashSet;
import java.util.Set;

@ApplicationScoped
public class SecurityConfig {

    @Inject
    AuthService authService;

    @Inject
    JwtUtil jwtUtil;

    @Inject
    JWTParser jwtParser;

    @ConfigProperties(prefix = "jwt")
    public static class JwtConfig {
        public String secret;
        public long expiration;
    }

    @Inject
    JwtConfig jwtConfig;

    @Produces
    public Key jwtKey() {
        // Utilisez la clé secrète pour HMAC
        return jwtUtil.getSecretKey(jwtConfig.secret);
    }

    @Produces
    public JWTParser jwtParser(Key key) {
        return new JWTParser();
    }

    // Exemple de filtre pour sécuriser les endpoints
    @Provider
    public static class AuthFilter {
        @ServerRequestFilter(preMatching = true)
        public void filter(ContainerRequestContext requestContext, @Context SecurityContext securityContext) {
            // Ici, vous pouvez ajouter la logique pour vérifier le JWT dans l'en-tête Authorization
            // et définir le contexte de sécurité si besoin
        }
    }
}