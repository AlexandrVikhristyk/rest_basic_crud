package com.petproject.test.services;

import com.petproject.test.dao.RoleRepository;
import com.petproject.test.dao.UserRepository;
import com.petproject.test.entity.CustomUser;
import com.petproject.test.entity.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    private final UserRepository userRepository;

    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(readOnly = true)
    public CustomUser findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Transactional
    public boolean save(CustomUser user) {
        if (userRepository.existsByEmail(user.getEmail())){
            return false;
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Set<Role> roles = new HashSet<>();
        roles.add(new Role());
        System.out.println(roles.size());
        user.setRoles(roles);

        userRepository.save(user);
        return true;
    }
}
