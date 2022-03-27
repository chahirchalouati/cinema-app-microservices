package com.movies.services.impl;

import com.github.javafaker.Faker;
import com.movies.dtos.requests.FileExampleRequest;
import com.movies.dtos.requests.FilesCreateRequest;
import com.movies.dtos.requests.FilesUpdateRequest;
import com.movies.dtos.responses.FileResponse;
import com.movies.repository.FilesRepository;
import com.movies.services.FilesService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Chahir Chalouati
 */
@Service
@RequiredArgsConstructor
public class FilesServiceImpl implements FilesService {

    private final FilesRepository filesRepository;
    private final Faker faker;

    @Override
    public Page<FileResponse> all(Pageable pageable) {
        List<FileResponse> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            final FileResponse fileResponse = new FileResponse();
            fileResponse.setId(faker.number().digit());
            fileResponse.setName(faker.funnyName().name());
            list.add(fileResponse);
        }
        return new PageImpl<>(list,pageable,list.size());
    }

    @Override
    public FileResponse findById(String id) {
        return null;
    }

    @Override
    public FileResponse save(FilesCreateRequest createFilesRequest) {
        return null;
    }

    @Override
    public FileResponse update(FilesUpdateRequest updateFilesRequest) {
        return null;
    }

    @Override
    public void deleteById(String id) {

    }

    @Override
    public FileResponse findByExample(Example<FileExampleRequest> example) {
        return null;
    }
}
