package com.marcopololearning.auth.controller;

import com.marcopololearning.auth.model.AuthRequest;
import com.marcopololearning.auth.model.AuthResponse;
import com.marcopololearning.auth.model.UserView;
import com.marcopololearning.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/v1/auth")
@Slf4j
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(path = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> authenticate(@RequestBody @NotNull @Valid AuthRequest request) {
        log.info("Authentication request started for user : {}", request.getUsername());
        AuthResponse response = authService.authenticate(request.getUsername(), request.getPassword());
        log.info("Authentication request ended for user : {} ", request.getUsername());
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/query", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> query(@RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String token) {
        log.info("Authorisation request started for token : {}", token);
        UserView response = authService.query(token);
        log.info("Authorisation request ended for token : {} ", token);
        return ResponseEntity.ok(response);
    }
}
