package com.movies.authorization.utils;

import java.util.Set;

/**
 * @author Chahir Chalouati
 */
public class Oauth2Utils {
    public static final String AUTHORIZATION_CODE = "authorization_code";
    public static final String CLIENT_CREDENTIALS = "client_credentials";
    public static final String REFRESH_TOKEN = "refresh_token";
    public static final String IMPLICIT = "IMPLICIT";
    public static final String SCOPE_READ = "READ";
    public static final String SCOPE_ALL = "ALL";
    public static final String SCOPE_WRITE = "WRITE";
    public static final String TRUST = "TRUST";
    public static final String GRANT_TYPE_PASSWORD = "password";
    public static final Set<String> RESOURCE_IDS = Set.of("microservices");
    public static final Set<String> grantTypes = Set.of(GRANT_TYPE_PASSWORD, AUTHORIZATION_CODE, REFRESH_TOKEN, IMPLICIT, CLIENT_CREDENTIALS);

}
