package com.marcopololearning.auth.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;


@Data
public class AuthRequest {
    @NotBlank(message = "Username can not be null,  please provide valid username")
    private String username;

    @NotBlank(message = "Password can not be null,  please provide valid password")
    private String password;
}
