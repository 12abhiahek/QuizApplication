package com.example.question_service.controller;

import com.example.question_service.entity.Question;
import com.example.question_service.entity.QuestionWrapper;
import com.example.question_service.entity.Response;
import com.example.question_service.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @GetMapping("/allquestion")
    public ResponseEntity< List<Question>> getAllQuestion(){
        return questionService.getAllQuestion();
    }
    @GetMapping("/catrgory/{category}")
    public ResponseEntity<List<Question>>getQuestionByCategory(@PathVariable String category){
        return questionService.getQuestionByCategory(category);
    }
    @PostMapping("/addquestion")
    public Question addNewQuestion(@RequestBody Question question){
        return questionService.addquestion(question);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Question> updateQuestion(@PathVariable Long id, @RequestBody Question question) {
        return questionService.updateQuestion(id, question);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable Long id) {
        return questionService.deleteQuestion(id);
    }

    @GetMapping("/generate")
    public ResponseEntity<List<Integer>>getQuestionForQuiz(@RequestParam String categoryName,@RequestParam Integer numQuestion){
        return questionService.getQuestionForQuiz(categoryName,numQuestion);
    }
    @PostMapping("/getquestion")
    public ResponseEntity<List<QuestionWrapper>>getQuestionFromId(List<Integer>questionIds){
       return questionService.getQuestionsFromId(questionIds);
    }
    @PostMapping("/getscore")
    public  ResponseEntity<Integer>getScore(@RequestBody List<Response>responses){
        return questionService.getScore(responses);
    }
}



