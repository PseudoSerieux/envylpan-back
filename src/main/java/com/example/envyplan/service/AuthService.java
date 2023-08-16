    package com.example.envyplan.service;

    import com.example.envyplan.model.User;
    import com.example.envyplan.repository.RoleRepository;
    import com.example.envyplan.repository.UserRepository;
    import io.jsonwebtoken.Jwts;
    import io.jsonwebtoken.SignatureAlgorithm;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.beans.factory.annotation.Value;
    import org.springframework.security.core.GrantedAuthority;
    import org.springframework.security.core.authority.SimpleGrantedAuthority;
    import org.springframework.security.core.userdetails.UserDetails;
    import org.springframework.security.core.userdetails.UserDetailsService;
    import org.springframework.security.core.userdetails.UsernameNotFoundException;
    import org.springframework.security.crypto.password.PasswordEncoder;
    import org.springframework.stereotype.Service;

    import java.util.Date;
    import java.util.Set;
    import java.util.stream.Collectors;

    @Service("userService")
    public class AuthService implements UserDetailsService {

        @Autowired
        private UserRepository userRepository;

        @Autowired
        private PasswordEncoder passwordEncoder;

        private final RoleRepository roleRepository;

        @Value("${jwt.secret}")
        private String secretKey;

        @Value("${jwt.expiration}")
        private String keyExpiration;

        @Autowired
        public AuthService(RoleRepository roleRepository) {
            this.roleRepository = roleRepository;
        }
        /*public boolean authenticate(String username, String password) {
            User user = userRepository.findByUsername(username);

            if (user == null) {
                return false;
            }

            return passwordEncoder.matches(password, user.getPassword());
        }

*/
        public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
            User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                    .orElseThrow(() ->
                            new UsernameNotFoundException("User not found with username or email: "+ usernameOrEmail));

            Set<GrantedAuthority> authorities = (Set<GrantedAuthority>) user
                    .getRole();

            return new org.springframework.security.core.userdetails.User(user.getEmail(),
                    user.getPassword(),
                    authorities);
        }
    }
