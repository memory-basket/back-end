package com.flower.question.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "question")
@Getter @Setter
@ToString
public class Question {

    @Id
    @Column(name = "question_id")
    private int id;

    @Column(unique = true)
    private String questionList;

}
