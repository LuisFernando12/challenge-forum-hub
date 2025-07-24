package hub.forum.api.infra.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import hub.forum.api.infra.token.dto.TokenDTO;
import hub.forum.api.infra.token.interfaces.TokenInterface;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService implements TokenInterface {
    private final String SECRETE = "A6864BB99C74D44C892F4AB668497";
    private  final String ISSUER = "auth0";
    @Override
    public String generateToken(TokenDTO payload) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(this.SECRETE);
            return JWT.create()
                    .withIssuer(this.ISSUER)
                    .withSubject(payload.subject())
                    .withPayload(payload.toString())
                    .withExpiresAt(expiresAt())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException(exception);
        }
    }

    private Instant expiresAt() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

    @Override
    public String verifyToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(this.SECRETE);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(this.ISSUER)
                    .build();
            return verifier.verify(token).getSubject();
        } catch (JWTVerificationException exception){
            throw new RuntimeException(exception.getMessage());
        }
    }


}
