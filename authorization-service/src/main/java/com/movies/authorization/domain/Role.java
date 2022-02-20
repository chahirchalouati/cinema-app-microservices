package com.movies.authorization.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Chahir Chalouati
 */
@Document(value = "roles")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true, fluent = true)
public class Role implements Serializable {

    @Id
    private String id;
    private String name;

    private List<Permission> permissions = new ArrayList<>();
    @CreatedDate
    private LocalDateTime createdAt;
}
