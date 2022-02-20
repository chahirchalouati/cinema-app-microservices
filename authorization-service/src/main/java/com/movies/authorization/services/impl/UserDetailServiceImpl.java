package com.movies.authorization.services.impl;

import com.movies.authorization.domain.DefaultUserDetails;
import com.movies.authorization.repository.UserRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Primary
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserRepository userDetailRepository;

    public UserDetailServiceImpl(UserRepository userDetailRepository) {
        this.userDetailRepository = userDetailRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {

        var user = userDetailRepository.findByUsername(name)
                .orElseThrow(() -> new UsernameNotFoundException("Username or password wrong"));

        return new DefaultUserDetails(user);

    }
}
