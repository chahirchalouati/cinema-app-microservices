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

import static com.movies.authorization.utils.Oauth2Utils.*;

/**
 * @author Chahir Chalouati
 */
@Configuration
@AllArgsConstructor
public class InitDefaultClient implements ApplicationRunner {


    private final ClientRepository clientRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public void run(ApplicationArguments args) {
        clientRepository.deleteAll();
        userRepository.deleteAll();


        Client client = new Client();
        client.setResourceIds(RESOURCE_IDS);
        client.setClientId("gateway-service");
        client.setClientSecret(passwordEncoder.encode("gateway-secret"));
        client.setAuthorizedGrantTypes(grantTypes);
        client.setScope(new HashSet<>(Arrays.asList(SCOPE_READ, SCOPE_WRITE, TRUST, SCOPE_ALL)));
        client.setSecretRequired(true);
        client.setAccessTokenValiditySeconds(50000);
        client.setRefreshTokenValiditySeconds(50000);
        client.setScoped(false);
        client.setEnabled(true);
        clientRepository.save(client);


        Client client_files = new Client();
        client_files.setResourceIds(RESOURCE_IDS);
        client_files.setClientId("files-service");
        client_files.setClientSecret(passwordEncoder.encode("files-secret"));
        client_files.setAuthorizedGrantTypes(grantTypes);
        client_files.setScope(new HashSet<>(Arrays.asList(SCOPE_READ, SCOPE_WRITE, TRUST, SCOPE_ALL)));
        client_files.setSecretRequired(true);
        client_files.setAccessTokenValiditySeconds(50000);
        client_files.setRefreshTokenValiditySeconds(50000);
        client_files.setScoped(false);
        client_files.setEnabled(true);
        clientRepository.save(client_files);

        Client client_movies = new Client();
        client_movies.setResourceIds(RESOURCE_IDS);
        client_movies.setClientId("products-service");
        client_movies.setClientSecret(passwordEncoder.encode("products-secret"));
        client_movies.setAuthorizedGrantTypes(grantTypes);
        client_movies.setScope(new HashSet<>(Arrays.asList(SCOPE_READ, SCOPE_WRITE, TRUST, SCOPE_ALL)));
        client_movies.setSecretRequired(true);
        client_movies.setAccessTokenValiditySeconds(50000);
        client_movies.setRefreshTokenValiditySeconds(50000);
        client_movies.setScoped(false);
        client_movies.setEnabled(true);
        clientRepository.save(client_movies);


        User user = new User();
        user.username("admin");
        user.password(passwordEncoder.encode("admin"));
        user.roles(List.of(new Role().name("ROLE_ADMIN")));
        userRepository.save(user);


    }
}
