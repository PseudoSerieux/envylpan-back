    package com.example.envyplan.service;

    import com.example.envyplan.model.User;
    import com.example.envyplan.repository.UserRepository;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.beans.factory.annotation.Value;
    import org.springframework.security.core.userdetails.UserDetails;
    import org.springframework.security.core.userdetails.UserDetailsService;
    import org.springframework.security.core.userdetails.UsernameNotFoundException;
    import org.springframework.security.crypto.password.PasswordEncoder;
    import org.springframework.stereotype.Service;

    import static java.util.Collections.emptyList;

    @Service("userService")
    public class AuthService implements UserDetailsService {

        @Autowired
        private UserRepository userRepository;

        @Autowired
        private PasswordEncoder passwordEncoder;

        @Value("${jwt.secret}")
        private String secretKey;

        @Value("${jwt.expiration}")
        private String keyExpiration;

        public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
            User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                    .orElseThrow(() ->
                            new UsernameNotFoundException("User not found with username or email: "+ usernameOrEmail));

            return new org.springframework.security.core.userdetails.User(user.getEmail(),
                    user.getPassword(),
                    emptyList());
        }
    }
