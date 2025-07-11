package com.abhi.security.service;

import com.abhi.security.entity.AirtelUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
public class JwtService {

    @Value("${jwt.secret}")
    private String secretKey ;

    protected String generateToken(AirtelUser airtelUser) {
        Map<String, Object> claims = new HashMap<>();

        claims.put("roles", airtelUser.getRoles().stream()
                .map(Enum::name)
                .collect(Collectors.toList()));

        String jwts = Jwts.builder()
                .claims()
                .add(claims)
                .subject(airtelUser.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 2))// 2 hours
                .and()
                .signWith(getKey())
                .compact();
        log.info("Generated JWT: {}", jwts);
        return jwts;
    }

    public String extractUsername(String jwtToken) {
        return extractClaim(jwtToken, Claims::getSubject);
    }

    public boolean validateToken(String jwtToken, String username) {
        final String extractedUsername = extractUsername(jwtToken);
        return (extractedUsername.equals(username) && !isTokenExpired(jwtToken));
    }

    @SuppressWarnings("unchecked")
    public List<String> extractRoles(String jwtToken) {
        return extractClaim(jwtToken, claims -> claims.get("roles", List.class));
    }

    private SecretKey getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private <T> T extractClaim(String jwtToken, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(jwtToken);
        return claimsResolver.apply(claims);
    }

    public Claims extractAllClaims(String jwtToken) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(jwtToken)
                .getPayload();
    }

    private boolean isTokenExpired(String jwtToken) {
        return extractExpiration(jwtToken).before(new Date());
    }

    private Date extractExpiration(String jwtToken) {
        return extractClaim(jwtToken, Claims::getExpiration);
    }

}
