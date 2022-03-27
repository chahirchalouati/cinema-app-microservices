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
public class Role {
    @Id
    private String id;
    private String name;
    private List<Permission> permissions = new ArrayList<>();

    public Role(String name, List<Permission> permissions) {
        this.name = name;
        this.permissions = permissions;
    }
}