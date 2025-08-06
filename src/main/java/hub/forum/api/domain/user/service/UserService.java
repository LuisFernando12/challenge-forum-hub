package hub.forum.api.domain.user.service;

import hub.forum.api.domain.profile.model.Profile;
import hub.forum.api.domain.profile.repository.ProfileRepository;
import hub.forum.api.domain.user.dto.UpdateUserDTO;
import hub.forum.api.domain.user.dto.UserCreateDTO;
import hub.forum.api.domain.user.dto.UserResponseDTO;
import hub.forum.api.domain.user.model.User;
import hub.forum.api.domain.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProfileRepository profileRepository;

    public UserResponseDTO createUser(UserCreateDTO userCreateDTO){
        User user = new User(userCreateDTO);
        Profile profile = this.profileRepository.getReferenceById(userCreateDTO.profile_id());
        user.setProfile(profile);
        var userDB = this.userRepository.save(user);
        return new UserResponseDTO(userDB);
    }

    public List<UserResponseDTO> findAllUsers(){
        return this.userRepository
                .findAll()
                .stream()
                .filter(User::getActive)
                .map(UserResponseDTO::new)
                .toList();
    }

    public UserResponseDTO getUser(Long id) {
        Optional<User> user = this.userRepository.findById(id);
        if(user.isEmpty()){
            throw new RuntimeException("User not found !");
        }
        return new UserResponseDTO(user.get());
    }

    public UserResponseDTO updateUser(UpdateUserDTO updateUserDTO, Long id){
        Optional<User> userDB  = this.userRepository.findById(id);
        if(userDB.isEmpty()){
            throw new RuntimeException("User not found !");
        }
        User user = userDB.get();
        user.update(updateUserDTO);
        return new UserResponseDTO(user);
    }

    public void deleteUser(Long id){
        Optional<User> userDB = this.userRepository.findById(id);
        if(userDB.isEmpty()){
            throw new RuntimeException("User not found");
        }
        userDB
           .get()
           .softDelete();
    }
}
