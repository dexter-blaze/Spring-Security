package com.cn.hotel.jwt;

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
    private String secret = "this-is-the-secret-key-which-is-being-used-to-formulate-the-jwt-token-signature"; //minimum 256 bit long
    private static final long JWT_TOKEN_VALIDITY = 60*60; //1hr
    public String getUsernameFromToken(String token) {
        Claims claims =  getClaimsFromToken(token);
        return claims.getSubject(); //fetching username from claims
    }

    public Claims getClaimsFromToken(String token) {
        Claims claims = Jwts.parser()  //initialize jwt parser
                .setSigningKey(secret.getBytes()) //provide secret key to verify the signature
                .build() //builds the jwt parser with above configuration
                .parseClaimsJws(token) /** takes token -> splits -> verifies signature ->check expiration time
                                       //in case of invalid token, throws error*/
                .getBody(); //converts the payload -> java claims object -> stores
        return claims; //returns the JWT Payload
    }
    //claims stores the JWT payload

    //getting the expiration time from the JWT payload
    public boolean isTokenExpired(String token){
        Claims claims = getClaimsFromToken(token);
        Date expDate = claims.getExpiration(); //extracting issued at from payload
        return expDate.before(new Date()); //compares with current time and returns if token is expired or not
    }


    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY*1000)) //*1000 to convert time in milliseconds
                .signWith(new SecretKeySpec(secret.getBytes(), SignatureAlgorithm.HS512.getJcaName()), SignatureAlgorithm.HS512)
                .compact(); //compact -> same as .build()
    }
}
