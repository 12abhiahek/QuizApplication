package com.example.question_service.service;

import com.example.question_service.entity.Question;
import com.example.question_service.entity.QuestionWrapper;
import com.example.question_service.entity.Response;
import com.example.question_service.repository.QuestionRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    public ResponseEntity<List<Integer>> getQuestionForQuiz(String categoryName, Integer numQuestion) {
        List<Integer>questions=questionRepository.findRandomQuestionsCategory(categoryName,numQuestion);

        return new ResponseEntity<>(questions,HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(List<Integer>questionIds) {
        List<QuestionWrapper>wrappers=new ArrayList<>();
        List<Question>questions=new ArrayList<>();

        for(Integer id:questionIds){
            questions.add(questionRepository.findById(id.longValue()).get());
        }

        for(Question question:questions){
            QuestionWrapper wrapper=new QuestionWrapper();
            wrapper.setId(question.getId());
            wrapper.setQuestion_Title(question.getQuestion_Title());
            wrapper.setOption1(question.getOption1());
            wrapper.setOption2(question.getOption2());
            wrapper.setOption3(question.getOption3());
            wrapper.setOption4(question.getOption4());


            wrappers.add(wrapper);
        }
        return  new ResponseEntity<>(wrappers,HttpStatus.OK);
    }

    public ResponseEntity<Integer> getScore(List<Response> responses) {

        int right=0;

        for(Response response:responses){
            Question question= (Question) questionRepository.findById(response.getId()).get();
            if(response.getResponse().equals(question.getRightAnswer())){
                right ++;

            }

        }
        return new ResponseEntity<>(right,HttpStatus.OK);

    }
}
