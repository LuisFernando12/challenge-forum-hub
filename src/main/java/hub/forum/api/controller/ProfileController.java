package hub.forum.api.controller;

import hub.forum.api.domain.profile.dto.ProfileDTO;
import hub.forum.api.domain.profile.dto.ResponseProfileDTO;
import hub.forum.api.domain.profile.model.Profile;
import hub.forum.api.domain.profile.repository.ProfileRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController()
@RequestMapping("/profiles")
public class ProfileController {
    @Autowired
    private ProfileRepository profileRepository;

    @PostMapping()
    @Transactional()
    public ResponseEntity createProfile(@RequestBody @Valid ProfileDTO createProfileDTO, UriComponentsBuilder uriComponentsBuilder){
        Profile profile = new Profile(null,createProfileDTO.name(),null);
        Profile profileDB  = this.profileRepository.save(profile);
        var uri = uriComponentsBuilder.path("/profiles/{id}").buildAndExpand(profileDB.getId()).toUri();
        return  ResponseEntity.created(uri).body(new ResponseProfileDTO(profileDB));
    }
    @GetMapping()
    @Transactional()
    public ResponseEntity findAllProfile(){
        List<ResponseProfileDTO> profile = this.profileRepository.findAll().stream().map(ResponseProfileDTO::new).toList();
        return ResponseEntity.ok().body(profile);
    }

    @GetMapping("/{id}")
    @Transactional()
    public ResponseEntity getProfile(@PathVariable("id") Long id){
        Profile profile = this.profileRepository.getReferenceById(id);
        return ResponseEntity.ok().body(new ResponseProfileDTO(profile));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity updateProfile(@PathVariable("id") Long id, @RequestBody @Valid ProfileDTO updateDTO){
        Profile profile = this.profileRepository.getReferenceById(id);
        profile.update(updateDTO);
        return ResponseEntity.ok(new ResponseProfileDTO(profile));
    }

    @DeleteMapping("/{id}")
    @Transactional()
    public ResponseEntity deleteProfile(@PathVariable("id") Long id){
        Profile profile = this.profileRepository.getReferenceById(id);
        this.profileRepository.delete(profile);
        return ResponseEntity.noContent().build();
    }

}
