package com.marcopololearning.auth.model.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidationFailure {
    private String source;
    private String message;

    public static ValidationFailure of(String source, String message) {
        return new ValidationFailure(source, message);
    }
}
