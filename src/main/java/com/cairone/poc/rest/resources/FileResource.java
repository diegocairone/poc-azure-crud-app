package com.cairone.poc.rest.resources;

import com.cairone.poc.core.model.FileModel;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("api/files")
public interface FileResource {

    @GetMapping("{id}")
    ResponseEntity<FileModel> getMetadataById(@PathVariable("id") Long id);

    @GetMapping("{id}/content")
    void getContentById(HttpServletResponse response, @PathVariable("id") Long id);

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<FileModel> uploadFile(@RequestParam("file") MultipartFile file);
}
