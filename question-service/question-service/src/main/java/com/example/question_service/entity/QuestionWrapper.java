package com.example.question_service.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QuestionWrapper {
    private Long Id;
    private String Question_Title;
    private String option1;
    private String option2;
    private String option3;
    private String option4;

    public QuestionWrapper(Long id, String questionTitle, String option1, String option2, String option3, String option4) {
    }
}
