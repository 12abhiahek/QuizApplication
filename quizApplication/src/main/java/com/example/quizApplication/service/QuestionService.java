package com.example.quizApplication.service;

import com.example.quizApplication.entity.Question;
import com.example.quizApplication.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;

    public ResponseEntity<List<Question>> getAllQuestion() {
        try {
            return new ResponseEntity<>(questionRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
                e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getQuestionByCategory(String category) {
        try {
            return new ResponseEntity<>(questionRepository.findByCategory(category), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    public Question addquestion(Question question) {
        return questionRepository.save(question);
    }

    public ResponseEntity<Question> updateQuestion(Long id, Question question) {
        Optional<Question> existingQuestion = questionRepository.findById(id);
        if (existingQuestion.isPresent()) {
            Question updatedQuestion = existingQuestion.get();
            updatedQuestion.setQuestion_Title(question.getQuestion_Title());
            updatedQuestion.setDifficultylevel(question.getDifficultylevel());
            updatedQuestion.setCategory(question.getCategory());
            return ResponseEntity.ok(questionRepository.save(updatedQuestion));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<String> deleteQuestion(Long id) {
        if(questionRepository.existsById(id)){
            questionRepository.deleteById(id);
            return ResponseEntity.ok("Question deleted successfully");
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
