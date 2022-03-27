package com.movies.controllers;

import com.movies.dtos.requests.FileExampleRequest;
import com.movies.dtos.requests.FilesCreateRequest;
import com.movies.dtos.requests.FilesUpdateRequest;
import com.movies.services.FilesService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

/**
 * @author Chahir Chalouati
 */
@RestController
@RequestMapping("/files")
public class FilesRestController {

    private final FilesService filesService;

    public FilesRestController(FilesService filesService) {
        this.filesService = filesService;
    }

    @GetMapping
//    @PreAuthorize("hasAuthority('VIEW_FILE') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> all(Pageable pageable) {
        return ResponseEntity.ok(this.filesService.all(pageable));
    }

    @GetMapping("/{id}")
//    @PreAuthorize("hasAuthority('VIEW_FILE') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> findOne(@PathVariable("id") String id) {
        return ResponseEntity.ok(this.filesService.findById(id));
    }

    @PostMapping(consumes = MULTIPART_FORM_DATA_VALUE)
//    @PreAuthorize("hasAuthority('CREATE_FILE') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> add(@Valid FilesCreateRequest createFilesRequest) {
        return new ResponseEntity<>(this.filesService.save(createFilesRequest), HttpStatus.CREATED);
    }

    @PutMapping()
//    @PreAuthorize("hasAuthority('UPDATE_FILE') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> update(@Valid FilesUpdateRequest updateFilesRequest) {
        return new ResponseEntity<>(this.filesService.update(updateFilesRequest), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
//    @PreAuthorize("hasAuthority('DELETE_FILE') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> deleteById(@PathVariable String id) {
        this.filesService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/example")
//    @PreAuthorize("hasAuthority('DELETE_FILE') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> findByExample(@RequestBody Example<FileExampleRequest> example) {
        return ResponseEntity.ok(this.filesService.findByExample(example));
    }
}
