package com.movies.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Chahir Chalouati
 */
@Document
@AllArgsConstructor
@NoArgsConstructor
@Data
public  class Permission {
    @Id
    private String id;
    private String name;

    public Permission(String name) {
        this.name = name;
    }
}