package com.petproject.test.controllers;

import com.petproject.test.entity.CustomUser;
import com.petproject.test.exeption.ResourceNotFoundException;
import com.petproject.test.services.UserService;
import com.petproject.test.validator.UserValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/user")
public class UserController {
    private static final Logger logger = LogManager.getLogger(UserController.class);

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(new UserValidator(userService));
    }

    @GetMapping
    public List<CustomUser> index() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomUser> getUserById(@PathVariable("id") Long userId) throws ResourceNotFoundException {
        CustomUser userFromDb = userService.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found for this id: " + userId));
        return ResponseEntity.ok().body(userFromDb);
    }

    @PostMapping
    public ResponseEntity login(@RequestBody CustomUser user) {
        userService.save(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomUser> updateUser(@PathVariable("id") Long userId, @RequestBody @Valid CustomUser newUser) throws ResourceNotFoundException {
        CustomUser oldUser = userService.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found for this id: " + userId));
        oldUser.setEmail(newUser.getEmail());
        BeanUtils.copyProperties(newUser, oldUser, "password", "confirmPassword", "id");
        final CustomUser updatedUser = userService.save(oldUser);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteUser(@PathVariable("id") Long userId) throws ResourceNotFoundException {
        CustomUser userFromDb = userService.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found for this id: " + userId));
        userService.delete(userFromDb);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}