package com.petproject.test.exeption;

import com.petproject.test.exeption.customException.ResourceNotFoundException;
import com.petproject.test.exeption.customException.UserValidationException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler({UserValidationException.class, ResourceNotFoundException.class})
    protected ResponseEntity<Object> handleUserValidationException(UserValidationException e){
        ErrorDetails body = new ErrorDetails(LocalDateTime.now(), HttpStatus.BAD_REQUEST ,Collections.singletonList(e.getMessage()));
        return buildResponseEntity(body);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        ErrorDetails body = new ErrorDetails(LocalDateTime.now(), status ,errors);
        return buildResponseEntity(body);
    }

    private ResponseEntity<Object> buildResponseEntity(ErrorDetails errorDetails) {
        return new ResponseEntity<>(errorDetails, errorDetails.getStatus());
    }
}
