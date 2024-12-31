package com.example.quizApplication.service;

import com.example.quizApplication.entity.Question;
import com.example.quizApplication.entity.QuestionWrapper;
import com.example.quizApplication.entity.Quiz;
import com.example.quizApplication.entity.Response;
import com.example.quizApplication.repository.QuestionRepository;
import com.example.quizApplication.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    private QuizRepository quizRepository;
    @Autowired
    private QuestionRepository questionRepository;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        List<Question>questions=questionRepository.findRandomQuestionsCategory(category,numQ);
        Quiz quiz=new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizRepository.save(quiz);

        return new ResponseEntity<>("success", HttpStatus.OK);

    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {

         Optional<Quiz>quiz =quizRepository.findById(id);
         List<Question>questionsFromDB=quiz.get().getQuestions();
         List<QuestionWrapper>questionForUser=new ArrayList<>();
         for(Question q:questionsFromDB){
             QuestionWrapper qw=new QuestionWrapper(q.getId(), q.getQuestion_Title(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
             questionForUser.add(qw);
         }
         return new ResponseEntity<>(questionForUser,HttpStatus.OK);

    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        Quiz quiz=quizRepository.findById(id).get();
        List<Question>questions=quiz.getQuestions();
        int right=0;
        int i=0;
        for(Response response:responses){
            if(response.getResponse().equals(questions.get(i).getRightAnswer())){
                right ++;

            }
           i++;

        }
        return new ResponseEntity<>(right,HttpStatus.OK);
    }
}
