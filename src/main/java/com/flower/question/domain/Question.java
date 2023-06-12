package com.flower.question.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "question")
@Getter @Setter
@ToString
public class Question {

    @Id
    @Column(name = "question_id")
    private Long id;

    @Column(unique = true)
    private String questionList;

}
