package com.sloths.movei_review_project.auth.utils;

import com.sloths.movei_review_project.auth.entities.MyUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {

    private final String SECRET_KEY = "thisIsASecretKeyToSingJsonWebTokensForAuthentication";

    public String getUsername(String token) {
        return getClaim(token, Claims::getSubject);
    }

    public Date getExpiration(String token) {
        return getClaim(token, Claims::getExpiration);
    }

    public boolean isTokenExpired(String token) {
        return getExpiration(token).before(new Date());
    }

    private <T> T getClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    public String createToken(MyUserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return doCreateToken(claims, userDetails.getUsername());
    }

    /*
    while generating a token:
    1. set claims of the token like subject, expiration, issuer etc.
    2. set values of the claims
    3. sign the token with your secret key
     */
    private String doCreateToken(Map<String, Object> claims, String subject) {
        Date currentTime = new Date(System.currentTimeMillis());
        Date expirationTime = new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 12); //expiration time is 12 hours later than issued time of the token
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(currentTime).setExpiration(expirationTime).signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    public boolean validateToken(String token, MyUserDetails userDetails) {
        final String username = getUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

}
