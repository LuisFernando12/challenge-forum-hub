package hub.forum.api.domain.response.service;

import hub.forum.api.domain.response.dto.CreateResponseDTO;
import hub.forum.api.domain.response.dto.ReturnResponseDTO;
import hub.forum.api.domain.response.dto.UpdateResponseDTO;
import hub.forum.api.domain.response.model.Response;
import hub.forum.api.domain.response.repository.ResponseRepository;
import hub.forum.api.domain.topic.model.Topic;
import hub.forum.api.domain.topic.repository.TopicRepository;
import hub.forum.api.domain.user.model.User;
import hub.forum.api.domain.user.repository.UserRepository;
import hub.forum.api.dto.PageDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class ResponseService {
    @Autowired
    private ResponseRepository responseRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TopicRepository topicRepository;

    public ReturnResponseDTO createResponse(CreateResponseDTO createResponseDTO){
        Optional<User> author = this.userRepository.findById(createResponseDTO.author_id());
        if(author.isEmpty()){
            throw new RuntimeException("Invalid User");
        }
        Optional<Topic> topic = this.topicRepository.findById(createResponseDTO.topic_id());
        if(topic.isEmpty()){
            throw new RuntimeException("Invalid Topic");
        }
        Response response = new Response(
                createResponseDTO.message(),
                author.get(),
                createResponseDTO.solution(),
                topic.get()
        );
        var responseDB = this.responseRepository.save(response);
        return new ReturnResponseDTO(responseDB);
    }

    public ReturnResponseDTO getResponse(Long id) {
        Optional<Response> responseDB = this.responseRepository.findById(id);
        if(responseDB.isPresent()){
            return new ReturnResponseDTO(responseDB.get());
        }
        throw new RuntimeException("Response not Found");
    }
    public PageDTO<ReturnResponseDTO> findAllResponse(Pageable pageable){
        return PageDTO.from(
                this.responseRepository
                        .findAll(pageable)
                        .map(ReturnResponseDTO::new)
        );
    }
    public ReturnResponseDTO updateResponse(UpdateResponseDTO updateResponseDTO, Long id){
        System.out.println("id: " + id + "exists: " + this.responseRepository.existsById(id));
        if(!this.responseRepository.existsById(id)){
            throw new RuntimeException("Response not found");
        }
        var response = this.responseRepository.getReferenceById(id);
        response.update(updateResponseDTO);
        return new ReturnResponseDTO(response);
    }
    public void deleteResponse(Long id){
        if(!this.responseRepository.existsById(id)){
            throw new RuntimeException("Response not found");
        }
        this.responseRepository.deleteById(id);
    }
}
