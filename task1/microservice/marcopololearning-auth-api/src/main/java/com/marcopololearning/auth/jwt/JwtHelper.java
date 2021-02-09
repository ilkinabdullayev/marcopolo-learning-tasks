package com.marcopololearning.auth.jwt;

import com.marcopololearning.auth.entity.User;
import com.marcopololearning.auth.exception.InvalidTokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class JwtHelper {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.token.validitySeconds}")
    private long validity;


    public String generateJwt(User user) {
        log.info("generating jwt token for {}", user.getUsername());
        Map<String, Object> params = new HashMap<>();
        params.put("id", user.getId());
        params.put("username", user.getUsername());
        params.put("role", user.getRole());
        String token = Jwts.builder()
                .setClaims(params)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + validity * 1000L))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
        log.info("Jwt token generated for {}", user.getUsername());
        return token;
    }


    public JwtToken getJwtToken(String token) {
        try {
            return new JwtToken(token, secret);
        } catch (MalformedJwtException ex) {
            log.error("Jwt token is not in valid format", ex);
            throw new InvalidTokenException("Token is invalid, " + ex.getMessage());
        }
    }
}
