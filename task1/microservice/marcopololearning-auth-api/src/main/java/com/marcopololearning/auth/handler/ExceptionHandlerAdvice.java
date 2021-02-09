package com.marcopololearning.auth.handler;

import com.marcopololearning.auth.exception.InvalidTokenException;
import com.marcopololearning.auth.exception.InvalidUsernameOrPasswordException;
import com.marcopololearning.auth.exception.TokenExpiredException;
import com.marcopololearning.auth.exception.UserNotFoundException;
import com.marcopololearning.auth.model.exception.ExceptionResponse;
import com.marcopololearning.auth.model.exception.ValidationFailure;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@Slf4j
public class ExceptionHandlerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<?> handleAllException(Exception exception, WebRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        return new ResponseEntity<>(ExceptionResponse.of(status.value(), exception.getMessage()), status);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<?> handleUserNotFoundException(UserNotFoundException exception, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(ExceptionResponse.of(status.value(), exception.getMessage()), status);
    }

    @ExceptionHandler(InvalidUsernameOrPasswordException.class)
    public final ResponseEntity<?> handleInvalidUsernameOrPasswordException(InvalidUsernameOrPasswordException exception
            , WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(ExceptionResponse.of(status.value(), exception.getMessage()), status);
    }

    @ExceptionHandler(InvalidTokenException.class)
    public final ResponseEntity<?> handleInvalidTokenException(InvalidTokenException exception
            , WebRequest request) {
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        return new ResponseEntity<>(ExceptionResponse.of(status.value(), exception.getMessage()), status);
    }

    @ExceptionHandler(TokenExpiredException.class)
    public final ResponseEntity<?> handleTokenExpiredException(TokenExpiredException exception
            , WebRequest request) {
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        return new ResponseEntity<>(ExceptionResponse.of(status.value(), exception.getMessage()), status);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<ValidationFailure> errors = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(ValidationFailure.of(error.getField(), error.getDefaultMessage()));
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(ValidationFailure.of(error.getObjectName(), error.getDefaultMessage()));
        }
        ExceptionResponse response = ExceptionResponse.of(status.value(), "Request validation failed");
        response.setValidationErrors(errors);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
