package com.movies.repository;

import com.movies.domain.File;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Chahir Chalouati
 */
public interface FilesRepository extends MongoRepository<File, Long> {
}
