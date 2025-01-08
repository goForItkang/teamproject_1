package com.team.teamproject_1.jwt;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;

@Slf4j
@Component
public class JwtTokenProvider {

    @Value("${jwt.key}")
    private String KEY;
    private SecretKey SECRET_KEY;
    private final long ACCESS_EXPIRE_TIME = 1000 * 60 * 30L;
    private final long REFRESH_EXPIRE_TIME = 1000 * 60 * 60L * 24 * 7;
    private String KEY_ROLE = "role";
    private final String HEADER_TYPE = "typ";
    private final String HEADER_JWT_TYPE = "JWT";

    @PostConstruct
    private void setSecretKey() {
        SECRET_KEY = Keys.hmacShaKeyFor(KEY.getBytes());
    }

    public String createToken(String username,String role) {
        Date beginDate = new Date();
        Date endDate = new Date(beginDate.getTime() + ACCESS_EXPIRE_TIME);

        return Jwts.builder()
                .header()
                .add(HEADER_TYPE, HEADER_JWT_TYPE)
                .and()
                .claim("username", username)
                .claim("role", role)
                .issuedAt(beginDate)
                .expiration(endDate)
                .signWith(new SecretKeySpec(SECRET_KEY.getEncoded(), "HmacSHA256"))
                .compact();
    }

    public boolean isExpired(String token) {

        Claims payload = tokenToClaims(token);

        try{
            if(payload != null && payload.getExpiration().before(new Date())){
                return true;
            }
        }catch(NullPointerException e){
            log.error("payload || getExpiration() is null");
            return false;
        }

        return false;
    }


    public boolean validateToken(String token) {
        try {

            Claims payload = Jwts.parser()
                    .verifyWith(SECRET_KEY)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    private Claims tokenToClaims(String token){
        try {
            return Jwts.parser()
                    .verifyWith(SECRET_KEY)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

        } catch (JwtException | IllegalArgumentException e) {
            return null;
        }
    }



}
