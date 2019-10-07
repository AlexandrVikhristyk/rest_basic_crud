package com.petproject.test.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "questions")
public class Question {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String question;
    @ManyToOne
    @JoinColumn(name = "test_id")
    private Test test;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "question", cascade = CascadeType.ALL)
    private List<Answer> answers;
}
