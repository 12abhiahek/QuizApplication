package com.example.quizApplication.controller;

import com.example.quizApplication.entity.QuestionWrapper;
import com.example.quizApplication.entity.Response;
import com.example.quizApplication.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {
    @Autowired
    private QuizService quizService;

    @GetMapping("/create")
    public ResponseEntity<String> createQuiz(@RequestParam String category,@RequestParam int numQ,@RequestParam String title){
        return quizService.createQuiz(category,numQ,title);

    }
    @GetMapping("/get/{id}")
    public ResponseEntity<List<QuestionWrapper>>getQuizQuestions(@PathVariable Integer id){
        return  quizService.getQuizQuestions(id);

    }
    @PostMapping("/submit/{id}")
    public ResponseEntity<Integer>submitQuiz(@PathVariable Integer id, @RequestBody List<Response> responses){
        return  quizService.calculateResult(id,responses);

    }

}
