package com.example.quizApplication.repository;

import com.example.quizApplication.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Long> {
    List<Question>findByCategory(String category);

    List<Question> findRandomQuestionsCategory(String category, int numQ);
}
