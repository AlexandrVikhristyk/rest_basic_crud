package com.petproject.test.services;

import com.petproject.test.entity.CustomUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserService userService;

    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        CustomUser user = userService.findByEmail(email);

        if (user == null)
            throw new UsernameNotFoundException(email + " not found");
        Set<GrantedAuthority> roles = new HashSet<>();
        roles.add( new SimpleGrantedAuthority(user.getRole().toString()));

        return new User(user.getEmail(), user.getPassword(), roles);
    }
}