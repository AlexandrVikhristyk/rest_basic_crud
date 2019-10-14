package com.petproject.test.services;

import com.petproject.test.dao.RoleRepository;
import com.petproject.test.dao.UserRepository;
import com.petproject.test.entity.CustomUser;
import com.petproject.test.entity.Role;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(readOnly = true)
    public CustomUser findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Transactional
    public ResponseEntity<Object> save(CustomUser user) {

        System.out.println("-----------------------------------------------------------");
        System.out.println(user.getPassword());
        System.out.println(user.getConfirmPassword());
        System.out.println(user.getPassword().equals(user.getConfirmPassword()));
        System.out.println("-----------------------------------------------------------");

//        if (userRepository.findByEmail(user.getEmail()) != null)
//            throw new UserValidationException("User exist");
//        if (!user.getPassword().equals(user.getConfirmPassword()))
//            throw new UserValidationException("Password do`sent much");
//        if (user.getPassword().length() < 8 || user.getPassword().length() > 32)
//            throw new UserValidationException("Password should be between 8 and 32");

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.getOne(1L));
        user.setRoles(roles);

        return ResponseEntity.ok().body(userRepository.save(user));
    }

    @Transactional(readOnly = true)
    public List<CustomUser> findAll(){
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<CustomUser> findById(Long id){
        return userRepository.findById(id);
    }

    @Transactional
    public void delete(CustomUser user){
        userRepository.delete(user);
    }
}
