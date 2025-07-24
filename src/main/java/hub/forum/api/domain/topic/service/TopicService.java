package hub.forum.api.domain.topic.service;

import hub.forum.api.domain.course.model.Course;
import hub.forum.api.domain.course.repository.CourseRepository;
import hub.forum.api.domain.topic.dto.CreateTopicDTO;
import hub.forum.api.domain.topic.dto.ResponseTopicDTO;
import hub.forum.api.domain.topic.dto.UpdateTopicDTO;
import hub.forum.api.domain.topic.enums.Status;
import hub.forum.api.domain.topic.model.Topic;
import hub.forum.api.domain.topic.repository.TopicRepository;
import hub.forum.api.domain.user.model.User;
import hub.forum.api.domain.user.repository.UserRepository;
import hub.forum.api.dto.PageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TopicService {
    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CourseRepository courseRepository;

    public ResponseTopicDTO createTopic(CreateTopicDTO createTopicDTO){
        if(!this.userRepository.existsById(createTopicDTO.author_id())){
            throw new RuntimeException("User not found");
        }
        if(!this.courseRepository.existsById(createTopicDTO.course_id())){
            throw new RuntimeException("Course not found");
        }
        User userDB = this.userRepository.getReferenceById(createTopicDTO.author_id());
        Course courseDB = this.courseRepository.getReferenceById(createTopicDTO.course_id());
        if (this.topicRepository.existsByTitleOrMessage(createTopicDTO.title(), createTopicDTO.message())){
            throw new RuntimeException("Already exists a topic whit this title or message ");
        }
        Topic topic = new Topic(
                null,
                createTopicDTO.title(),
                createTopicDTO.message(),
                Status.ACTIVE,
                userDB,
                courseDB,
                null
        );
        var topicDB = this.topicRepository.save(topic);
        return new ResponseTopicDTO(topicDB);
    }

    public ResponseTopicDTO getTopic(Long id){
        var topicDB = this.topicRepository.getReferenceById(id);
        return new ResponseTopicDTO(topicDB);
    }

    public PageDTO<ResponseTopicDTO> findAllTopics(Pageable pageable){
        return PageDTO.from(
                this.topicRepository
                        .findAll(pageable)
                        .map(ResponseTopicDTO::new)
        );
    }

    public ResponseTopicDTO updateTopic(UpdateTopicDTO updateTopicDTO, Long id){
        if(!this.topicRepository.existsById(id)){
            throw new RuntimeException("Topic not found");
        }
        if(this.topicRepository.existsByTitleOrMessage(updateTopicDTO.title(), updateTopicDTO.message())){
            throw new RuntimeException("Already exists a topic whit this title or message ");
        }
        Topic topic = this.topicRepository.getReferenceById(id);
        topic.update(updateTopicDTO);
        return new ResponseTopicDTO(topic);
    }

    public void deleteTopic(Long id){
        if(!this.topicRepository.existsById(id)){
            throw new RuntimeException("Topic Not Found");
        }
        this.topicRepository.deleteById(id);
    }
}
