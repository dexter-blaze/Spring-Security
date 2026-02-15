package com.CN.Gym.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtAuthenticationHelper {
    private final String secret = "this-is-the-secret-key-which-is-being-used-to-formulate-the-jwt-token-signature";
    private static final long JWT_TOKEN_VALIDITY = 60*60; //1hr

    public String getUsernameFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.getSubject();
    }

    private Claims getClaimsFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(secret.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims;
    }

    public boolean isTokenExpired(String token) {
        Claims claims = getClaimsFromToken(token);
        Date expDate = claims.getExpiration();
        return expDate.before(new Date());
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date((System.currentTimeMillis())))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY*1000))
                .signWith(new SecretKeySpec(secret.getBytes(), SignatureAlgorithm.HS512.getJcaName()), SignatureAlgorithm.HS512)
                .compact();
    }
}
