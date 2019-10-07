package com.petproject.test.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
//@Data
@Setter
@Getter
@NoArgsConstructor
public class Role {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String role;

    @ManyToMany(mappedBy = "roles")
    private Set<CustomUser> users;
}
