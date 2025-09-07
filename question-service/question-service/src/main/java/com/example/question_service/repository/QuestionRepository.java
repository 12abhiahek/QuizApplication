package com.example.question_service.repository;

import com.example.question_service.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface QuestionRepository extends JpaRepository<Question,Long> {
    List<Question> findByCategory(String category);

    //@Query(value = "SELECT q.id from question q where q.category=:category order by RANDOM() LIMIT numQ");
    List<Integer> findRandomQuestionsCategory(String category, int numQ);

    ThreadLocal<Object> findById(Integer id);
}
