package com.example.quizApplication.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Question {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @jakarta.persistence.Id
    private Long Id;
    private String Question_Title;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String difficultylevel;
    private String category;


}
