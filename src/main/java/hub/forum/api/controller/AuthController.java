package hub.forum.api.controller;

import hub.forum.api.domain.auth.dto.LoginDTO;
import hub.forum.api.domain.auth.dto.ResponseLoginDTO;
import hub.forum.api.domain.auth.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthService authService;

    @PostMapping("/login")
    @Transactional
    public ResponseEntity login(@RequestBody @Valid LoginDTO loginDTO){
        String token = this.authService.login(loginDTO);
        return ResponseEntity.ok(new ResponseLoginDTO(token));
    }
}
