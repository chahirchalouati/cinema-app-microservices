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
@Document(value = "users")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true, fluent = true)

public class User implements Serializable {

    @Id
    private String id;
    private String username;
    private String lastName;
    private String firstName;
    private String email;
    private String password;
    boolean isAccountNonExpired = true;
    boolean isAccountNonLocked = true;
    boolean isCredentialsNonExpired = true;
    boolean isEnabled = true;
    private List<Role> roles = new ArrayList<>();;

    @CreatedDate
    private LocalDateTime createdAt;

}
