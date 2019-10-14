package com.petproject.test.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "tests")
public class Test {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Length(min = 4, max = 255, message = "Test should be between 4 and 255 characters")
    @NotBlank(message = "Please provide test")
    private String name;

    @ManyToOne
    @JoinColumn(name = "theme_id")
    private Theme theme;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "test", cascade = CascadeType.ALL)
    private List<Question> questions;
}
