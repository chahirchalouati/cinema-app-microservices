package com.movies.authorization.configuration;

import com.movies.authorization.domain.Client;
import com.movies.authorization.domain.Role;
import com.movies.authorization.domain.User;
import com.movies.authorization.repository.ClientRepository;
import com.movies.authorization.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * @author Chahir Chalouati
 */
@Configuration
@AllArgsConstructor
public class InitDefaultClient implements ApplicationRunner {


    private final ClientRepository clientRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public static final String AUTHORIZATION_CODE = "authorization_code";
    public static final String REFRESH_TOKEN = "refresh_token";
    public static final String IMPLICIT = "implicit";
    public static final String SCOPE_READ = "read";
    public static final String SCOPE_WRITE = "write";
    public static final String TRUST = "trust";
    public static final String GRANT_TYPE_PASSWORD = "password";

    @Override
    public void run(ApplicationArguments args) {
        clientRepository.deleteAll();
        userRepository.deleteAll();
        if (clientRepository.count() <= 0) {
            Client client = new Client();
            client.setResourceIds(new HashSet<>(List.of("resource_id")));
            client.setClientId("client");
            client.setClientSecret(passwordEncoder.encode("client-secret"));
            client.setAuthorizedGrantTypes(new HashSet<>(Arrays.asList(GRANT_TYPE_PASSWORD, AUTHORIZATION_CODE, REFRESH_TOKEN, IMPLICIT)));
            client.setScope(new HashSet<>(Arrays.asList(SCOPE_READ, SCOPE_WRITE, TRUST)));
            client.setSecretRequired(true);
            client.setAccessTokenValiditySeconds(50000);
            client.setRefreshTokenValiditySeconds(50000);
            client.setScoped(false);
            clientRepository.save(client);
        }

        if (userRepository.count() <= 0) {
            User user = new User();
            user.username("admin");
            user.password(passwordEncoder.encode("admin"));
            user.roles(List.of(new Role().name("ROLE_ADMIN")));
            userRepository.save(user);
        }

    }
}
