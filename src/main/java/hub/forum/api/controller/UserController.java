package hub.forum.api.controller;

import hub.forum.api.domain.user.dto.UpdateUserDTO;
import hub.forum.api.domain.user.dto.UserCreateDTO;
import hub.forum.api.domain.user.dto.UserResponseDTO;
import hub.forum.api.domain.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping
    @Transactional
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody @Valid UserCreateDTO userCreateDTO, UriComponentsBuilder uriComponentsBuilder){
       var user = this.userService.createUser(userCreateDTO);
       var uri = uriComponentsBuilder.path("users/{id}").buildAndExpand(user.id()).toUri();
       return ResponseEntity.created(uri).body(user);
    }
    @GetMapping
    @Transactional
    public ResponseEntity<List<UserResponseDTO>> findAllUser(){
       var users = this.userService.findAllUsers();
       return ResponseEntity.ok(users);
    }
    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable("id") Long id){
       var user = this.userService.getUser(id);
       return ResponseEntity.ok(user);
    }
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<UserResponseDTO> updateUser(@RequestBody UpdateUserDTO updateUserDTO, @PathVariable("id") Long id){
       var user = this.userService.updateUser(updateUserDTO, id);
       return ResponseEntity.ok(user);
    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<HttpStatusCode> deleteUser(@PathVariable("id") Long id){
       this.userService.deleteUser(id);
       return ResponseEntity.noContent().build();
    }
}
