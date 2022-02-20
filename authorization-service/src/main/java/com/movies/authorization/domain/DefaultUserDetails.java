package com.movies.authorization.domain;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Chahir Chalouati
 */
@Data
public class DefaultUserDetails implements UserDetails {

    private User user;

    public DefaultUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.user.roles().stream().map(rolesPermissionsExtractor).collect(Collectors.toList()).stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return this.user.password();
    }

    @Override
    public String getUsername() {
        return this.user.username();
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.user.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.user.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.user.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return this.user.isEnabled();
    }

    final Function<Role, List<SimpleGrantedAuthority>> rolesPermissionsExtractor = role -> {
        final SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.name());
        List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
        if (Objects.nonNull(role.permissions()) && !role.permissions().isEmpty()) {
            simpleGrantedAuthorities = role.permissions().stream().map(permission -> new SimpleGrantedAuthority(permission.name())).collect(Collectors.toList());
        }
        simpleGrantedAuthorities.add(grantedAuthority);
        return simpleGrantedAuthorities;
    };
}
