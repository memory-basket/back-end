package com.flower.team.domain;

import com.flower.answer.domain.Answer;
import com.flower.member.domain.Member;
import com.flower.question.domain.Question;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter
@Table(name = "team")
@ToString
public class Team {

    @Id
    @Column(name = "team_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String testResult;

    private String todayQuesion;

//    @OneToMany(mappedBy = "question", fetch = FetchType.LAZY)
//    private List<Question> repliedQuestion;

}
