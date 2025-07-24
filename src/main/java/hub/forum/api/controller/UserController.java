package hub.forum.api.controller;

import hub.forum.api.domain.profile.model.Profile;
import hub.forum.api.domain.profile.repository.ProfileRepository;
import hub.forum.api.domain.user.dto.UserCreateDTO;
import hub.forum.api.domain.user.dto.UserResponseDTO;
import hub.forum.api.domain.user.model.User;
import hub.forum.api.domain.user.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProfileRepository profileRepository;
    @PostMapping
    @Transactional
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody @Valid UserCreateDTO userCreateDTO, UriComponentsBuilder uriComponentsBuilder){
        User user = new User(userCreateDTO);
        Profile profile = this.profileRepository.getReferenceById(userCreateDTO.profile_id());
        user.setProfile(profile);
        var userDB = this.userRepository.save(user);
        var uri = uriComponentsBuilder.path("users/{id}").buildAndExpand(userDB.getId()).toUri();
        return ResponseEntity.created(uri).body(new UserResponseDTO(userDB));
    }
    @GetMapping
    @Transactional
    public ResponseEntity findAllUser(){
        var users = this.userRepository.findAll().stream().map(UserResponseDTO::new).toList();
        return ResponseEntity.ok(users);
    }
    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity findAllUser(@PathVariable("id") Long id){
        var user = this.userRepository.getReferenceById(id);
        return ResponseEntity.ok(user);
    }
    //TODO: Implementar o update do usuario validando o que pode ser atualizado
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteUser(@PathVariable("id") Long id){
        var user = this.userRepository.getReferenceById(id);
        user.softDelete();
        return ResponseEntity.noContent().build();
    }
}
