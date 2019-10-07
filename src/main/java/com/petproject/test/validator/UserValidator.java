package com.petproject.test.validator;


import com.petproject.test.entity.CustomUser;
import com.petproject.test.services.UserService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    private final UserService userService;

    public UserValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return CustomUser.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        CustomUser user = (CustomUser) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "Required");
        if (user.getEmail().length() < 8 || user.getEmail().length() > 32) {
            errors.rejectValue("email", "Incorrect email");
        }

        if (userService.findByEmail(user.getEmail()) != null) {
            errors.rejectValue("email", "This user is exists");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Required");
        if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "Password must be bigger then 8 and lover then 32");
        }

        if (!user.getConfirmPassword().equals(user.getPassword())) {
            errors.rejectValue("confirmPassword", "Passwords dos`ent much");
        }
    }
}
