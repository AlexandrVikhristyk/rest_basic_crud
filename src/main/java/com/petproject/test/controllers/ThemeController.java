package com.petproject.test.controllers;

import com.petproject.test.dao.ThemeRepository;
import com.petproject.test.entity.Theme;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/theme")
public class ThemeController {
    private final ThemeRepository themeRepository;

    public ThemeController(ThemeRepository themeRepository) {
        this.themeRepository = themeRepository;
    }

    @GetMapping
    public List<Theme> getAllThemes(){
        return themeRepository.findAll();
    }

    @GetMapping("{id}")
    public Theme getTheme(@PathVariable("id") Theme theme){
        return theme;
    }

    @PostMapping
    public Theme saveTheme(@RequestBody @Valid Theme theme){
        return themeRepository.save(theme);
    }

    @PutMapping("{id}")
    public Theme updateTheme(@RequestBody Theme theme, @PathVariable("id") Theme themeFromDb){
        BeanUtils.copyProperties(theme, themeFromDb, "id");
        return themeFromDb;
    }

    @DeleteMapping("{id}")
    public void deleteTheme(@PathVariable("{id}") Theme theme){
        themeRepository.delete(theme);
    }
}
