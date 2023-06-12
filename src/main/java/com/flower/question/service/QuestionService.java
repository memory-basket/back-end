package com.flower.question.service;

import com.flower.question.domain.Question;
import com.flower.question.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class QuestionService {

    private final QuestionRepository questionRepository;

    public String getRandomQuestion() {

        Question question = questionRepository.findById((int)(Math.random() * 125));
        log.info(question.getId() + question.getQuestionList());
        return question.getQuestionList();
    }
}
