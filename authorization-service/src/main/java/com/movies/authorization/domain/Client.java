package com.movies.authorization.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.*;

/**
 * @author Chahir Chalouati
 */
@Document
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Client implements Serializable {
    @Id
    private String id;
    private Set<String> resourceIds = new HashSet<>();
    @Field(value = "client_id")
    @Indexed(unique = true, direction = IndexDirection.DESCENDING, background = true)
    private String clientId;
    private String clientSecret;
    private boolean isSecretRequired = false;
    private boolean isScoped = false;
    private Set<String> scope = new HashSet<>();
    private Set<String> authorizedGrantTypes = new HashSet<>();
    private Set<String> registeredRedirectUri = new HashSet<>();
    private Collection<GrantedAuthority> authorities = new ArrayList<>();
    private Integer accessTokenValiditySeconds = -1;
    private Integer refreshTokenValiditySeconds = -1;
    private boolean isAutoApprove = false;
    private Map<String, Object> additionalInformation = new HashMap<>();
}
