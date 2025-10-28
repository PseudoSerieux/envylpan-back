    package com.example.envyplan.service;

    import com.example.envyplan.model.User;
    import com.example.envyplan.repository.UserRepository;
    import jakarta.enterprise.context.ApplicationScoped;
    import jakarta.inject.Inject;
    import org.eclipse.microprofile.config.inject.ConfigProperty;

    import static java.util.Collections.emptyList;

    @ApplicationScoped
    public class AuthService implements UserDetailsService {

        @Inject
        private UserRepository userRepository;

        @ConfigProperty(name = "jwt.secret")
        String secretKey;

        @ConfigProperty(name = "jwt.expiration")
        String keyExpiration;

        @Override
        public UserDetails loadUserByUsername(String usernameOrEmail) {
            User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                    .orElseThrow(() ->
                            new RuntimeException("User not found with username or email: " + usernameOrEmail));

            return new org.springframework.security.core.userdetails.User(user.getEmail(),
                    user.getPassword(),
                    emptyList());
        }
    }
