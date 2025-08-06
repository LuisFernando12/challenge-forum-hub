package hub.forum.api.controller;

import hub.forum.api.domain.response.dto.CreateResponseDTO;
import hub.forum.api.domain.response.dto.ReturnResponseDTO;
import hub.forum.api.domain.response.dto.UpdateResponseDTO;
import hub.forum.api.domain.response.model.Response;
import hub.forum.api.domain.response.service.ResponseService;
import hub.forum.api.dto.PageDTO;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController()
@RequestMapping("/response")
public class ResponseController {
    @Autowired
    private ResponseService responseService;
    @PostMapping
    @Transactional
    public ResponseEntity<ReturnResponseDTO> createResponse(@RequestBody @Valid CreateResponseDTO createResponseDTO, UriComponentsBuilder uriComponentsBuilder){
        var response = this.responseService.createResponse(createResponseDTO);
        var uri = uriComponentsBuilder.path("/response/{id}").buildAndExpand(response.id()).toUri();
        return ResponseEntity.created(uri).body(response);
    }
    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<ReturnResponseDTO> getResponse(@PathVariable("id") Long id){
        var response = this.responseService.getResponse(id);
        return ResponseEntity.ok(response);
    }
    @GetMapping
    @Transactional
    public ResponseEntity<PageDTO<ReturnResponseDTO>> findAllResponse(@PageableDefault(size = 10, page = 0) Pageable pageable){
        return ResponseEntity.ok( this.responseService.findAllResponse(pageable) );
    }
    @PutMapping("/{id}")
    @Transactional
    public  ResponseEntity<ReturnResponseDTO> updateResponse(@RequestBody UpdateResponseDTO updateResponseDTO, @PathVariable("id") Long id){
        return ResponseEntity.ok(
                this.responseService.updateResponse(updateResponseDTO, id)
        );
    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteResponse(@PathVariable("id") Long id){
        this.responseService.deleteResponse(id);
        return ResponseEntity.noContent().build();
    }
}
