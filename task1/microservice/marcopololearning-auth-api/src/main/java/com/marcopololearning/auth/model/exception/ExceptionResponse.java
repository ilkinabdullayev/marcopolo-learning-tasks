package com.marcopololearning.auth.model.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse {
    private int code;
    private String message;
    private List<ValidationFailure> validationErrors;

    public static ExceptionResponse of(int code, String message) {
        return new ExceptionResponse(code, message, null);
    }
}
