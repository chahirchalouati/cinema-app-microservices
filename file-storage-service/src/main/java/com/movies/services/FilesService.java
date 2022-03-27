package com.movies.services;

import com.movies.dtos.requests.FileExampleRequest;
import com.movies.dtos.requests.FilesCreateRequest;
import com.movies.dtos.requests.FilesUpdateRequest;
import com.movies.dtos.responses.FileResponse;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Chahir Chalouati
 */
public interface FilesService {

    Page<FileResponse> all(Pageable pageable);

    FileResponse findById(String id);

    FileResponse save(FilesCreateRequest createFilesRequest);

    FileResponse update(FilesUpdateRequest updateFilesRequest);

    void deleteById(String id);

    FileResponse findByExample(Example<FileExampleRequest> example);
}
