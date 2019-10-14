package com.petproject.test.controllers;

import com.petproject.test.dao.QuestionRepository;
import com.petproject.test.entity.Question;
import com.petproject.test.exeption.customException.ResourceNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {
    private final QuestionRepository questionRepository;

    public QuestionController(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @GetMapping
    public List<Question> getAllQuestions(){
        return questionRepository.findAll();
    }

    @GetMapping("/{id}")
    public Question getOneQuestion(@PathVariable("id") Long questionId) throws ResourceNotFoundException{
        return questionRepository.findById(questionId).orElseThrow(() -> new ResourceNotFoundException("Question not found for this id - " + questionId));
    }

    @PostMapping
    public Question saveQuestion(@RequestBody Question question){
        return questionRepository.save(question);
    }

    @PutMapping("/{id}")
    public Question updateQuestion(@PathVariable("id") Question questionFromDb,
                                   @RequestBody Question question) {
        BeanUtils.copyProperties(question, questionFromDb, "id");
        return questionRepository.save(questionFromDb);
    }

    @DeleteMapping("/{id}")
    public void deleteQuestion(@PathVariable("id") Long questionId) throws ResourceNotFoundException {
        Question question = questionRepository.findById(questionId).orElseThrow(() -> new ResourceNotFoundException("Question not found for this id - " + questionId));
        questionRepository.delete(question);
    }

}
