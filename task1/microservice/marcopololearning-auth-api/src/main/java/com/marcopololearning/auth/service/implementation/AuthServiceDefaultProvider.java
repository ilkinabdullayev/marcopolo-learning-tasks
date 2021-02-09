package com.marcopololearning.auth.service.implementation;

import com.marcopololearning.auth.exception.InvalidTokenException;
import com.marcopololearning.auth.exception.TokenExpiredException;
import com.marcopololearning.auth.jwt.JwtToken;
import com.marcopololearning.auth.model.UserView;
import com.marcopololearning.auth.service.AuthService;
import com.marcopololearning.auth.service.UserService;
import com.marcopololearning.auth.jwt.JwtHelper;
import com.marcopololearning.auth.model.AuthResponse;
import com.marcopololearning.auth.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceDefaultProvider implements AuthService {

    private static final String BEARER_AUTHENTICATION_PREFIX = "Bearer ";

    private final UserService userService;
    private final JwtHelper jwtHelper;

    @Override
    public AuthResponse authenticate(String username, String password) {
        log.info("Authenticate service started for {}", username);
        User user = userService.authenticate(username, password);
        String token = jwtHelper.generateJwt(user);
        log.info("Authenticate service ended for {}", username);
        return AuthResponse.of(token);
    }

    @Override
    public UserView query(String token) {
        log.info("Authorisation service started for {}", token);

        token = Optional.ofNullable(token)
                .filter(header -> header.startsWith(BEARER_AUTHENTICATION_PREFIX))
                .map(header -> header.replaceFirst(BEARER_AUTHENTICATION_PREFIX, "").trim())
                .orElseThrow(() -> new InvalidTokenException("Token is invalid"));

        JwtToken jwtToken = jwtHelper.getJwtToken(token);

        if(jwtToken.isTokenExpired()) {
            throw new TokenExpiredException("Token expired");
        }

        UserView userView = userService.findUserByUsername(jwtToken.getUsername());
        log.info("Authorisation service ended for {}", token);
        return userView;
    }
}
