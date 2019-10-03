package com.petproject.test.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Test {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "theme_id")
    private Theme theme;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "test", cascade = CascadeType.ALL)
    private List<Question> questions;
}
