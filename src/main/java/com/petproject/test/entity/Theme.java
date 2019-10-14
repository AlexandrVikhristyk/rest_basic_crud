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
@Table(name = "themes")
public class Theme {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Length(min = 4, max = 255, message = "Theme should be between 4 and 255 characters")
    @NotBlank(message = "Please provide theme")
    private String theme;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "theme", cascade = CascadeType.ALL)
    private List<Test> tests;
}