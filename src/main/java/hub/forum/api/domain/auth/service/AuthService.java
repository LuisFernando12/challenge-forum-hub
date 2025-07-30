package hub.forum.api.domain.auth.service;

import hub.forum.api.domain.auth.dto.LoginDTO;
import hub.forum.api.domain.user.model.User;
import hub.forum.api.domain.user.repository.UserRepository;
import hub.forum.api.infra.token.TokenService;
import hub.forum.api.infra.token.dto.TokenDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenService tokenService;

    public String login(LoginDTO loginDTO){
        if(!this.userRepository.existsByEmail(loginDTO.email())){
           throw new RuntimeException("User not found !");
        }
        User userDB = this.userRepository.findByEmail(loginDTO.email());
        TokenDTO tokenDTO = new TokenDTO(userDB.getEmail(), userDB.getName(),userDB.getProfile().getName());

        return this.tokenService.generateToken(tokenDTO);

    }
}
