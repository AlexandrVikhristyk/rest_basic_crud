package com.petproject.test.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Answer {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String answer;
    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;
    private Boolean correctly;
}
