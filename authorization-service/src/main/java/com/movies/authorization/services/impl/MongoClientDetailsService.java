package com.movies.authorization.services.impl;

import com.movies.authorization.repository.ClientRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * @author Chahir Chalouati
 */
@Service
@NoArgsConstructor
@Primary
public class MongoClientDetailsService implements ClientDetailsService {

    @Autowired
    private ClientRepository clientRepository;

    private PasswordEncoder passwordEncoder = NoOpPasswordEncoder.getInstance();

    private Map<String, ClientDetails> clientDetailsStore = new HashMap<>();

    public ClientDetails loadClientByClientId(final String clientId) throws ClientRegistrationException {
        final var client = clientRepository.findByClientId(clientId)
                .orElseThrow(() -> new NoSuchClientException("No client with requested id: " + clientId));
        final var resourceIds = String.join(",", client.getResourceIds());
        final var scopes = String.join(",", client.getScope());
        final var grantTypes = String.join(",", client.getAuthorizedGrantTypes());
        final var authorities = client.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));
        final var clientDetails = new BaseClientDetails(client.getClientId(), resourceIds, scopes, grantTypes, authorities);
        clientDetails.setClientSecret(client.getClientSecret());
        clientDetails.setAccessTokenValiditySeconds(client.getAccessTokenValiditySeconds());
        clientDetails.setRefreshTokenValiditySeconds(client.getRefreshTokenValiditySeconds());
        clientDetails.setAdditionalInformation(client.getAdditionalInformation());
        clientDetails.setAutoApproveScopes(client.getScope());
        return clientDetails;
    }

    public void setClientDetailsStore(final Map<String, ? extends ClientDetails> clientDetailsStore) {
        this.clientDetailsStore = new HashMap<>(clientDetailsStore);
    }

    public void setPasswordEncoder(final PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


}
