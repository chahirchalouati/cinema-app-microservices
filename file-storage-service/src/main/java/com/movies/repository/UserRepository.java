package com.movies.repository;

import com.movies.domain.File;
import com.movies.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Chahir Chalouati
 */
public interface UserRepository extends MongoRepository<User, Long> {
}
