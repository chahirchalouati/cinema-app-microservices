package com.movies.authorization.repository;

import com.movies.authorization.domain.Client;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

/**
 * @author Chahir Chalouati
 */
public interface ClientRepository extends MongoRepository<Client, String> {
    Optional<Client> findByClientIdAndIsEnabledIsTrue(String clientId);
}
