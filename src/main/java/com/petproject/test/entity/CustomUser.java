package com.petproject.test.entity;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "users")
public class CustomUser {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Views.Id.class)
    private Long id;

    @Email(message = "Incorrect email")
    @NotEmpty(message = "Please provide email")
    @JsonView(Views.IdEmail.class)
    private String email;

    @NotEmpty(message = "Please provide password")
    private String password;

    @Transient
    private String confirmPassword;

    @JsonView(Views.FullUser.class)
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles;

    public CustomUser(String email, String password,String confirmPassword, Set<Role> roles) {
        this.email = email;
        this.password = password;
        this.confirmPassword =confirmPassword;
        this.roles = roles;
    }
}
