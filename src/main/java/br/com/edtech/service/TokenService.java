package br.com.edtech.service;

import br.com.edtech.exception.BusinessRuleException;
import br.com.edtech.model.user.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("12345678");
            return JWT.create()
                    .withIssuer("edtech")
                    .withSubject(user.getUsername())
                    .withExpiresAt(expiration(120))
                    .sign(algorithm);
        } catch (JWTCreationException ex) {
            throw new BusinessRuleException("Erro ao generar token JWT");
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("12345678");
            return JWT.require(algorithm)
                    .withIssuer("edtech")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTCreationException ex) {
            throw new BusinessRuleException("Erro ao generar token JWT");
        }
    }

    private Instant expiration(Integer minutes) {
        return LocalDateTime.now().plusMinutes(minutes).toInstant(ZoneOffset.of("-03:00"));
    }
}
