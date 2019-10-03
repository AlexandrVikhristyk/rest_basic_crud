package com.petproject.test.services;

import com.petproject.test.dao.UserRepository;
import com.petproject.test.entity.CustomUser;
import com.petproject.test.entity.Role;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public CustomUser findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Transactional
    public boolean addUser(String email, String password, Role role) {
        if (userRepository.existsByEmail(email)){
            return false;
        }

        CustomUser user = new CustomUser(email, password, role);
        userRepository.save(user);
        return true;
    }
}
