package com.flower.question.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
class QuestionServiceTest {

    @Autowired
    QuestionService questionService;

    @Test
    @DisplayName("질문 랜덤으로 가져오기 테스트")
    public void getRandomQuestion(){
        questionService.getRandomQuestion();
    }

}