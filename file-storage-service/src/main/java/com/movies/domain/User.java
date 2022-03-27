package com.movies.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Chahir Chalouati
 */
@Document
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

    @Id
    private String id;
    private String firstname;
    private String lastname;
    private String description;
    private String username;
    private List<Role> roles = new ArrayList<>();
    private List<Animal> animals = new ArrayList<>();

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static
    class Animal {
        private String name;

    }
}
