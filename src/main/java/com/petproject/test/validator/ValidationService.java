package com.petproject.test.validator;

import com.petproject.test.entity.CustomUser;
import com.petproject.test.exeption.customException.UserValidationException;
import com.petproject.test.services.UserServiceImpl;
import org.springframework.stereotype.Component;

@Component
public class ValidationService {

    private final UserServiceImpl userServiceImpl;

    public ValidationService(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    public void validate(CustomUser user) throws UserValidationException {
        if (userServiceImpl.findByEmail(user.getEmail()) != null)
            throw new UserValidationException("User Exist");
        if (!user.getPassword().equals(user.getConfirmPassword()))
            throw new UserValidationException("Passwords dos`ent much");
        if (user.getPassword().length() < 8 || user.getPassword().length() > 32)
            throw new UserValidationException("Password should be bigger than 8 and lower than 8");
    }
}
