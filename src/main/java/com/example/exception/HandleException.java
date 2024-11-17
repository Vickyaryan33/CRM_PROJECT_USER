package com.example.exception;

import com.example.Payload.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@RestControllerAdvice
public class HandleException {


    // Handle exception for resource not found
//    @ExceptionHandler(ResourceNotFound.class)
//    public ResponseEntity<ErrorDetails> handleEmployeeNotFoundException(
//            ResourceNotFound e,
    @ExceptionHandler(value = ResourceNotFound.class)
    public ResponseEntity<ErrorDetails> handleEmployeeNotFoundException(
            ResourceNotFound e,
            WebRequest request
    ) {
        ErrorDetails errorDetails = new ErrorDetails(
                new Date(),
                e.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }
}
