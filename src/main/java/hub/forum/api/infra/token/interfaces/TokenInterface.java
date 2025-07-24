package hub.forum.api.infra.token.interfaces;

import hub.forum.api.infra.token.dto.TokenDTO;

public interface TokenInterface {
    String generateToken(TokenDTO payload);
    String verifyToken(String token);
}
