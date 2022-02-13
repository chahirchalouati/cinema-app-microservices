package com.movie.authservice.configuration.properties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Chahir Chalouati
 */
@ConfigurationProperties(prefix = "jwt")
@Data
@Component
public class SecurityProps {

    private String secret;
    private long expiration;

}
