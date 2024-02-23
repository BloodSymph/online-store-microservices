package com.company.apigateway.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;

@Component
public class JWTUtil {

    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    public void validateJWTToken(final String token) {
        Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(
                        token
                );
    }

}
