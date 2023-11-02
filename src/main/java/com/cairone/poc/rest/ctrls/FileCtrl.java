package com.cairone.poc.rest.ctrls;

import com.cairone.poc.core.model.FileModel;
import com.cairone.poc.core.services.FileService;
import com.cairone.poc.rest.resources.FileResource;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class FileCtrl implements FileResource {

    private final FileService fileService;

    @Override
    public ResponseEntity<FileModel> uploadFile(MultipartFile file) {
        return ResponseEntity.ok(fileService.uploadFile(file));
    }
}
