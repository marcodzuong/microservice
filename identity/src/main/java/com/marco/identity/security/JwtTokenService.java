package com.marco.identity.security;

import com.marco.identity.entities.Authority;
import com.marco.identity.entities.User;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.stream.Collectors;

@Service
public class JwtTokenService {
    private static final Logger logger = LoggerFactory.getLogger(JwtTokenService.class);
    private static final String AUTHORITIES_KEY = "auth";

    @Value("${jwt.expiration}")
    private Long expiration;

    @Value("${jwt.secret}")
    private String SECRET_KEY;

    public String generateAccessToken(User user) {
        String authorities = user.getAuthorities().stream().map(Authority::getName).collect(Collectors.joining(","));
        long now = (new Date()).getTime();
        Date validity = new Date(now + this.expiration * 3600* 1000);
        return Jwts.builder()
                .setSubject(user.getUserName())
                .claim(AUTHORITIES_KEY, authorities)
                .setIssuedAt(new Date())
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    public boolean validateAccessToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException ex) {
            logger.error("JWT expired", ex);
        } catch (IllegalArgumentException ex) {
            logger.error("Token is null, empty or only whitespace", ex);
        } catch (MalformedJwtException ex) {
            logger.error("JWT is invalid", ex);
        } catch (UnsupportedJwtException ex) {
            logger.error("JWT is not supported", ex);
        } catch (SignatureException ex) {
            logger.error("Signature validation failed");
        }
        return false;
    }

    public String getUserNameFromToken(String token) {
        return parseClaims(token).getSubject();
    }

    public String getAuthorities(String token) {
        return parseClaims(token).get(AUTHORITIES_KEY).toString();
    }


    public Claims parseClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }
}

