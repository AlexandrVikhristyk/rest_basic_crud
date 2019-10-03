package com.petproject.test.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Theme {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String theme;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "theme", cascade = CascadeType.ALL)
    private List<Test> tests;
}