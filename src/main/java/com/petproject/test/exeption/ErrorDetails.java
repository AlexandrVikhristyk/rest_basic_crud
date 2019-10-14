package com.petproject.test.exeption;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ErrorDetails {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-M-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private HttpStatus status;
    private List<String> errors;

    public ErrorDetails(LocalDateTime timestamp, HttpStatus status, List<String> errors) {
        super();
        this.timestamp = timestamp;
        this.status = status;
        this.errors = errors;
    }
}
