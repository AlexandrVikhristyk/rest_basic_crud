package com.petproject.test.controllers;

import com.petproject.test.dao.TestRepository;
import com.petproject.test.dao.ThemeRepository;
import com.petproject.test.entity.Test;
import com.petproject.test.entity.Theme;
import com.petproject.test.exeption.customException.ResourceNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {
    private final TestRepository testRepository;
    private final ThemeRepository themeRepository;

    public TestController(TestRepository testRepository, ThemeRepository themeRepository) {
        this.testRepository = testRepository;
        this.themeRepository = themeRepository;
    }

    @GetMapping
    public List<Test> getAllTests(){
        return testRepository.findAll();
    }

    @GetMapping("/theme/{theme}")
    public List<Test> getAllByTheme(@PathVariable("theme") Long id) throws ResourceNotFoundException {
        Theme theme = themeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Theme not found for this id - " + id));
        System.out.println("---------------------------------------------------------------------------");
        System.out.println("BY THEME");
        System.out.println("THEME ID - " + theme.getId());
        System.out.println("THEME THEME - " + theme.getTheme());
        System.out.println("---------------------------------------------------------------------------");
        List<Test> allByTheme = testRepository.findAllByTheme(theme);
        allByTheme.forEach(x -> System.out.println("Test id - " + x.getId() + "\n" +
                "Test name - " + x.getName() + "\n"));
        System.out.println("Test size - " + allByTheme.size());
        return allByTheme;
    }

    @GetMapping("/{id}")
    public Test getTest(@PathVariable("id") Long testId) throws ResourceNotFoundException {
        return testRepository.findById(testId).orElseThrow(() ->new ResourceNotFoundException("Test not found for this id - " + testId));
    }

    @PostMapping
    public void saveTest(@RequestBody @Valid Test test){
        System.out.println("----------------------------------------------------------------------------------------------");
        System.out.println("START");
        System.out.println("TEST ID - " + test.getId());
        System.out.println("TEST NAME - " + test.getName());
        System.out.println("TEST THEME - " + test.getTheme());
        System.out.println("----------------------------------------------------------------------------------------------");
        testRepository.save(test);
    }

    @PutMapping("/{id}")
    public Test updateTest(@RequestBody Test test, @PathVariable("id") Long id) throws ResourceNotFoundException {
        Test testFromDb = testRepository.findById(id).orElseThrow(() ->new ResourceNotFoundException("Test not found for this id - " + id));
        BeanUtils.copyProperties(test, testFromDb, "id");
        return testRepository.save(testFromDb);
    }

    @DeleteMapping("/{id}")
    public void deleteTest(@PathVariable("id") Long testId) throws ResourceNotFoundException {
        Test testFromDb = testRepository.findById(testId).orElseThrow(() ->new ResourceNotFoundException("Test not found for this id - " + testId));
        testRepository.delete(testFromDb);
    }
}
