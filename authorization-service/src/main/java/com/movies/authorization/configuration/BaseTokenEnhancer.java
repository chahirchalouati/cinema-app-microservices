package com.movies.authorization.configuration;

import com.movies.authorization.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

/**
 * @author Chahir Chalouati
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class BaseTokenEnhancer implements TokenEnhancer {

    private final UserRepository userRepository;

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
//        final var principal = (UserDetails) authentication.getUserAuthentication();
//        var user = this.userRepository.findByUsername(principal.getUsername())
//                .orElseThrow(() -> new UsernameNotFoundException("user not found"));
        // use accessToken object to add any custom value to token
        return accessToken;
    }
}
