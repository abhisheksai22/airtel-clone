package com.infy.airtel.gateway.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.GatewayFilterFactory;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;

@Component("JwtAuthFilter")
@Slf4j
public class JwtAuthFilter implements GatewayFilterFactory<JwtAuthFilter.Config>, Ordered {

    @Value("${jwt.secret}")
    private String secret;

    private static final List<String> EXCLUDED_PATHS = List.of(
            "/api/security/register",
            "/api/security/login"
    );

    public JwtAuthFilter() {
        log.info("🔐 JwtAuthFilter constructor called");
    }

    @Override
    public String name() {

        return "JwtAuthFilter";
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {

            ServerHttpRequest request = exchange.getRequest();
            log.info("request URI : {}", request.getURI());
            String requestPath = exchange.getRequest().getURI().getPath();
            if (EXCLUDED_PATHS.contains(requestPath)) {
                exchange.getRequest().mutate()
                        .header("X-Request-Id", generateXRequestId());
                return chain.filter(exchange);
            }

            String authHeader = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
            log.info("Auth header :{}", authHeader);
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return onError(exchange, "Missing or invalid Authorization header", HttpStatus.UNAUTHORIZED);
            }
            String token = authHeader.substring(7);

            // validate the token
            try {
                Claims claims = extractAllClaims(token);
                if (isTokenExpired(token)) {
                    throw new RuntimeException("Token expired");
                }
//                 role check
                List<String> roles = extractRoles(token);
                String role = exchange.getRequest().getHeaders().getFirst("X-Role-Id");
                log.info("Extracted roles: {}", roles);
                if (role != null && !roles.contains(role)) {
                    log.info("Role from header : {}, extratced roles from token: {}", role, roles);
                    return onError(exchange, "Role not authorized", HttpStatus.FORBIDDEN);
                }

                //generate X-Request-Id
                exchange.getRequest().mutate()
                        .header("X-Request-Id", generateXRequestId());

            } catch (Exception e) {
                return onError(exchange, "Invalid token", HttpStatus.UNAUTHORIZED);
            }
            return chain.filter(exchange);
        };
    }

    @Override
    public Class<Config> getConfigClass() {
        return Config.class;
    }

    private String generateXRequestId() {
        return UUID.randomUUID().toString();
    }

    @SuppressWarnings("unchecked")
    public List<String> extractRoles(String jwtToken) {
        return extractClaim(jwtToken, claims -> claims.get("roles", List.class));
    }

    private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus status) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(status);
        return response.setComplete();
    }

    @Override
    public int getOrder() {
        return -1;
    }

    public static class Config {
    }

    public String extractUsername(String jwtToken) {
        return extractClaim(jwtToken, Claims::getSubject);
    }

    public boolean validateToken(String jwtToken, String username) {
        final String extractedUsername = extractUsername(jwtToken);
        return (extractedUsername.equals(username) && !isTokenExpired(jwtToken));
    }

    private SecretKey getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private <T> T extractClaim(String jwtToken, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(jwtToken);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String jwtToken) {
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