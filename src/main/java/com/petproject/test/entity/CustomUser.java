package com.petproject.test.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

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
    private Long id;

    @Email(message = "Incorrect email")
    @NotEmpty(message = "Please provide email")
    private String email;

    @Length(min = 8, max = 32, message = "Password should be between 8 and 32")
    @NotEmpty(message = "Please provide password")
    private String password;

    @Transient
    private String confirmPassword;

    @ManyToMany
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public CustomUser(String email, String password,String confirmPassword, Set<Role> roles) {
        this.email = email;
        this.password = password;
        this.confirmPassword =confirmPassword;
        this.roles = roles;
    }
}
