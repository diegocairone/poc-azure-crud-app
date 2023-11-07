package com.cairone.poc.rest.ctrls;

import com.cairone.poc.core.model.FileModel;
import com.cairone.poc.core.services.FileService;
import com.cairone.poc.rest.resources.FileResource;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@RestController
@RequiredArgsConstructor
public class FileCtrl implements FileResource {

    private final FileService fileService;

    @Override
    public ResponseEntity<FileModel> getMetadataById(Long id) {
        return fileService.findMetadataById(id).map(ResponseEntity::ok)
                .orElseThrow(() -> new RuntimeException(String.format("File with ID %s not exists", id)));
    }

    @Override
    public void getContentById(HttpServletResponse response, Long id) {

        FileModel file = fileService.findMetadataById(id)
                .orElseThrow(() -> new RuntimeException(String.format("File with ID %s not exists", id)));

        BufferedInputStream bis = fileService.findContentById(id)
                .orElseThrow(() -> new RuntimeException(String.format("No content could be found for the ID %s", id)));

        try (OutputStream os = response.getOutputStream()) {
            response.setContentType(file.getContentType());
            response.setHeader("Content-Disposition", String.format("inline; %s", file.getOriginalFilename()));
            IOUtils.copy(bis, os);
            os.flush();
            response.flushBuffer();
        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        }
    }

    @Override
    public ResponseEntity<FileModel> uploadFile(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String contentType = file.getContentType();
        long sizeInBytes = file.getSize();
        try {
            BufferedInputStream inputStream = new BufferedInputStream(file.getInputStream());
            return ResponseEntity.ok(
                    fileService.uploadFile(
                            fileName, contentType, sizeInBytes, inputStream));
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
