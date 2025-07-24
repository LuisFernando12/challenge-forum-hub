package hub.forum.api.controller;

import hub.forum.api.domain.course.dto.CreateCourseDTO;
import hub.forum.api.domain.course.dto.ResponseCourseDTO;
import hub.forum.api.domain.course.dto.UpdateCourseDTO;
import hub.forum.api.domain.course.model.Course;
import hub.forum.api.domain.course.repository.CourseRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/courses")
public class CourseController {
    @Autowired
    private CourseRepository courseRepository;

    @PostMapping
    @Transactional
    public ResponseEntity createCourse(@RequestBody @Valid CreateCourseDTO createCourseDTO, UriComponentsBuilder uriComponentsBuilder){
        Course course = new Course(null, createCourseDTO.name(), createCourseDTO.category(),null);
        var courseDB = this.courseRepository.save(course);
        var uri = uriComponentsBuilder.path("/courses/{id}").buildAndExpand(courseDB.getId()).toUri();
        return ResponseEntity.created(uri).body(new ResponseCourseDTO(courseDB));
    }
    @GetMapping
    @Transactional
    public ResponseEntity findAllCourse(){
        var courseDTOList = this.courseRepository.findAll().stream().map(ResponseCourseDTO::new).toList();
        return ResponseEntity.ok(courseDTOList);
    }
    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity getCourse(@PathVariable("id") Long id){
        var course = this.courseRepository.getReferenceById(id);
        return ResponseEntity.ok(new ResponseCourseDTO(course));
    }
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity updateCourse(@PathVariable("id") Long id, @RequestBody @Valid UpdateCourseDTO updateCourseDTO){
        var course = this.courseRepository.getReferenceById(id);
        course.update(updateCourseDTO);
        return ResponseEntity.ok(new ResponseCourseDTO(course));
    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteCourse(@PathVariable("id") Long id){
        var course = this.courseRepository.getReferenceById(id);
        this.courseRepository.delete(course);
        return ResponseEntity.noContent().build();
    }
}
