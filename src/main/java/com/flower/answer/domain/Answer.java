package com.flower.answer.domain;

import com.flower.member.domain.Member;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "answer")
@Getter @Setter
@ToString
public class Answer {

    @Id
    @Column(name = "answer_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String question;

    private String answer;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

}
