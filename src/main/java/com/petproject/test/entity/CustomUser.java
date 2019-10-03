package com.petproject.test.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class CustomUser {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String email;
    private String password;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    public CustomUser(String email, String password, Role role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
