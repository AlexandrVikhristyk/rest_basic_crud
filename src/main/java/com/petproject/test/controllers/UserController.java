package com.petproject.test.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.petproject.test.dao.RoleRepository;
import com.petproject.test.entity.CustomUser;
import com.petproject.test.entity.Views;
import com.petproject.test.exeption.customException.ResourceNotFoundException;
import com.petproject.test.exeption.customException.UserValidationException;
import com.petproject.test.services.SecurityService;
import com.petproject.test.services.UserService;
import com.petproject.test.services.UserServiceImpl;
import com.petproject.test.validator.ValidationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    private final UserService userService;
    private final RoleRepository roleRepository;
    private final SecurityService securityService;
    private final ValidationService validationService;

    public UserController(UserServiceImpl userService, RoleRepository roleRepository, SecurityService securityService, ValidationService validationService) {
        this.userService = userService;
        this.roleRepository = roleRepository;
        this.validationService = validationService;
        this.securityService = securityService;
    }

    @GetMapping
    @JsonView(Views.IdEmail.class)
    public List<CustomUser> index() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    @JsonView({Views.FullUser.class})
    public CustomUser getUserById(@PathVariable("id") Long userId) throws ResourceNotFoundException {
        return userService.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found for this id: " + userId));
    }

    @PostMapping
    public void saveUser(@RequestBody @Valid CustomUser user) throws UserValidationException {
        validationService.validate(user);
        userService.save(user);
    }

//    @DeleteMapping("/role/{id}")
//    public Map<String, Boolean> deleteRole(@PathVariable("id") Long roleId) throws ResourceNotFoundException {
//        Role role = roleRepository.findById(roleId).orElseThrow(() -> new ResourceNotFoundException("Role not found for this id: " + roleId));
//        roleRepository.delete(role);
//        Map<String, Boolean> response = new HashMap<>();
//        response.put("deleted", Boolean.TRUE);
//        return response;
//    }
//
//    @GetMapping("/role")
//    public List<Role> getRoles() {
//        List<Role> all = roleRepository.findAll();
//        all.forEach(System.out::println);
//        return all;
//    }

    @PutMapping("/{id}")
    public Map<String, Boolean> updateUser(@PathVariable("id") Long userId, @RequestBody CustomUser newUser) throws ResourceNotFoundException, UserValidationException {
        CustomUser oldUser = userService.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found for this id: " + userId));
        oldUser.setEmail(newUser.getEmail());
        BeanUtils.copyProperties(newUser, oldUser, "password", "confirmPassword", "id");
        userService.save(oldUser);
        Map<String, Boolean> response = new HashMap<>();
        response.put("updated", Boolean.TRUE);
        return response;
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