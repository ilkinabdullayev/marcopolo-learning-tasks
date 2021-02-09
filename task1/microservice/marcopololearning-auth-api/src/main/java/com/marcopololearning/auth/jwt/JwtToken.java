package com.marcopololearning.auth.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.util.Date;

public class JwtToken {

    private Claims claims;

    public JwtToken(String token, String secret) {
        claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    public Date getExpirationDate() {
        return claims.getExpiration();
    }

    public boolean isTokenExpired() {
        return getExpirationDate().before(new Date());
    }

    public String getUsername() {
        return claims.get("username", String.class);
    }
}
