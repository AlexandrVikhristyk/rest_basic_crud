package com.petproject.test.controllers;

import com.petproject.test.dao.AnswerRepository;
import com.petproject.test.entity.Answer;
import com.petproject.test.exeption.customException.ResourceNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/answer")
public class AnswerController {
    private final AnswerRepository answerRepository;

    public AnswerController(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    @GetMapping
    public List<Answer> getAllAnswers(){
        return answerRepository.findAll();
    }

    @GetMapping("/{id}")
    public Answer getOneAnswer(@PathVariable("id") Long answerId) throws ResourceNotFoundException {
        return answerRepository.findById(answerId).orElseThrow(() -> new ResourceNotFoundException("Answer not found for this id - " + answerId));
    }

    @PostMapping
    public Answer saveAnswer(@RequestBody Answer answer){
        return answerRepository.save(answer);
    }

    @PutMapping("/{id}")
    public Answer updateAnswer(@PathVariable Long id, @RequestBody Answer answer) throws ResourceNotFoundException {
        Answer answerFromDb = answerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Answer not found for this id - " + id));
        BeanUtils.copyProperties(answer, answerFromDb, "id");
        return answerRepository.save(answerFromDb);
    }

    @DeleteMapping("/{id}")
    public void deleteAnswer(@PathVariable Long id) throws ResourceNotFoundException {
        Answer answerFromDb = answerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Answer not found for this id - " + id));
        answerRepository.delete(answerFromDb);
    }
}
