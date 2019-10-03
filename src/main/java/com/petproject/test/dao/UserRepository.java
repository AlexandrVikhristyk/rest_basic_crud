package com.petproject.test.dao;

import com.petproject.test.entity.CustomUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<CustomUser, Long> {
    CustomUser findByEmail(String email);
    boolean existsByEmail(String email);
}
