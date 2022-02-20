package com.movies.authorization.repository;

import com.movies.authorization.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

/**
 * @author Chahir Chalouati
 */
public interface UserRepository extends MongoRepository<User,String> {
    Optional<User> findByUsername(String name);
}
