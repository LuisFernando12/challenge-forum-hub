package hub.forum.api.controller;

import hub.forum.api.domain.topic.dto.CreateTopicDTO;
import hub.forum.api.domain.topic.dto.UpdateTopicDTO;
import hub.forum.api.domain.topic.model.Topic;
import hub.forum.api.domain.topic.repository.TopicRepository;
import hub.forum.api.domain.topic.service.TopicService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/topics")
public class TopicController {
    @Autowired
    private TopicService topicService;

    @PostMapping
    @Transactional
    public ResponseEntity createTopic(@RequestBody @Valid CreateTopicDTO createTopicDTO, UriComponentsBuilder uriComponentsBuilder){
        var createdTopic = this.topicService.createTopic(createTopicDTO);
        var uri = uriComponentsBuilder.path("/topics/{id}").buildAndExpand(createdTopic.id()).toUri();
        return  ResponseEntity.created(uri).body(createdTopic);
    }
    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity getTopic(@PathVariable("id") Long id){
        var topic = this.topicService.getTopic(id);
        return ResponseEntity.ok(topic);
    }
    @GetMapping()
    @Transactional
    public ResponseEntity findAllTopics(@PageableDefault(size = 10, page = 0) Pageable pageable){
        var topics = this.topicService.findAllTopics(pageable);
        return ResponseEntity.ok(topics);
    }
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity updateTTopics(@RequestBody UpdateTopicDTO updateTopicDTO, @PathVariable("id") Long id){
        var topic = this.topicService.updateTopic(updateTopicDTO, id);
        return ResponseEntity.ok(topic);
    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteTopic(@PathVariable("id") Long id){
        this.topicService.deleteTopic(id);
        return ResponseEntity.noContent().build();
    }
}
