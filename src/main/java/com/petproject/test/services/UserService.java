package com.petproject.test.services;

import com.petproject.test.entity.CustomUser;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {
    CustomUser findByEmail(String email);

    ResponseEntity<Object> save(CustomUser user);

    List<CustomUser> findAll();

    Optional<CustomUser> findById(Long id);

    void delete(CustomUser user);
}
