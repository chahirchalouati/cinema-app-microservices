package com.movies.authorization.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.builders.ClientDetailsServiceBuilder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Chahir Chalouati
 */
public class MongoClientDetailsServiceBuilder extends ClientDetailsServiceBuilder<MongoClientDetailsServiceBuilder> {

    private final Map<String, ClientDetails> clientDetails = new HashMap<>();

    @Autowired
    private PasswordEncoder passwordEncoder; // for writing client secrets

    public MongoClientDetailsServiceBuilder passwordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        return this;
    }

    @Override
    protected void addClient(final String clientId, final ClientDetails build) {
        clientDetails.put(clientId, build);
    }

    @Override
    protected ClientDetailsService performBuild() {
        final MongoClientDetailsService mongoClientDetailsService = new MongoClientDetailsService();
        if (passwordEncoder != null) {
            mongoClientDetailsService.setPasswordEncoder(passwordEncoder);
        }
        mongoClientDetailsService.setClientDetailsStore(clientDetails);
        return mongoClientDetailsService;
    }

}