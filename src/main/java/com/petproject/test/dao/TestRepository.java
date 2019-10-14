package com.petproject.test.dao;

import com.petproject.test.entity.Test;
import com.petproject.test.entity.Theme;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TestRepository extends JpaRepository<Test, Long> {
    List<Test> findAllByTheme(Theme theme);
}
