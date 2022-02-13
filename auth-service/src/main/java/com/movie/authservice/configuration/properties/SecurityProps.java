package com.movie.authservice.configuration.properties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Chahir Chalouati
 */
@ConfigurationProperties(prefix = "jwt")
@Data
@NoArgsConstructor
public class SecurityProps {

    private String secret;
    private long expiration;

}
