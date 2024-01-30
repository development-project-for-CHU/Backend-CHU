package com.chu.appgestionpatientchu.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.chu.appgestionpatientchu.domain.Personne;
import com.chu.appgestionpatientchu.dto.PersonneDto;
import com.chu.appgestionpatientchu.services.PersonneService;

import com.chu.appgestionpatientchu.utils.enums.Roles;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Collections;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class UserAuthenticationProvider {

    @Value("${security.jwt.token.secret-key:secret-key}")
    private String secretKey;

    private final PersonneService personneService;

    @PostConstruct
    protected void init() {
        // this is to avoid having the raw secret key available in the JVM
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(PersonneDto personne) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + 3600000); // 1 hour

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        return JWT.create()
                .withSubject(personne.getEmail()) // Assuming email is the unique identifier
                .withIssuedAt(now)
                .withExpiresAt(validity)
                .withClaim("email", personne.getEmail()) // Adjust as needed
                .withClaim("nom", personne.getNom()) // Adjust as needed
                .withClaim("prenom", personne.getPrenom()) // Adjust as needed
                .withClaim("roles", personne.getRoles().name())
                .sign(algorithm);
    }

    public Authentication validateToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        JWTVerifier verifier = JWT.require(algorithm)
                .build();

        DecodedJWT decoded = verifier.verify(token);
        System.out.println("Decoded Token: " + decoded);

        PersonneDto personneDto = PersonneDto.builder()
                .email(decoded.getClaim("email").asString())
                .nom(decoded.getClaim("nom").asString())
                .prenom(decoded.getClaim("prenom").asString())
                .roles(Roles.values()[decoded.getClaim("roles").asInt()])
                .build();

        return new UsernamePasswordAuthenticationToken(personneDto, null, Collections.emptyList());
    }

    public Authentication validateTokenStrongly(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        JWTVerifier verifier = JWT.require(algorithm)
                .build();

        DecodedJWT decoded = verifier.verify(token);
        System.out.println("Decoded Token: " + decoded);

        Personne personne = personneService.findByEmail(decoded.getClaim("email").asString())
                .orElseThrow(() -> new RuntimeException("Personne not found"));

        return new UsernamePasswordAuthenticationToken(personne, null, Collections.emptyList());
    }

}
