package com.cairone.poc.core.services;

import com.azure.core.util.Context;
import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.models.BlobHttpHeaders;
import com.azure.storage.blob.options.BlobParallelUploadOptions;
import com.cairone.poc.core.model.FileModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileService {

    // blobContainerClient bean represents an Azure Blob Storage container
    private final BlobContainerClient blobContainerClient;

    public FileModel uploadFile(MultipartFile file) {

        // Blob index tags
        Map<String, String> tags = new HashMap<>();
        tags.put("originalFilename", file.getOriginalFilename());

        UUID blobId = UUID.randomUUID();

        // This bean represents an Azure Blob Storage blob
        BlobClient blobClient = blobContainerClient.getBlobClient(
                String.format("%s.blob", blobId));

        BlobHttpHeaders jsonHeaders = new BlobHttpHeaders()
                .setContentType(file.getContentType());

        try {

            BlobParallelUploadOptions options = new BlobParallelUploadOptions(file.getInputStream())
                    .setTags(tags)
                    .setHeaders(jsonHeaders);

            blobClient.uploadWithResponse(options, Context.NONE);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return FileModel.builder()
                .id(blobId)
                .originalFilename(file.getOriginalFilename())
                .build();
    }
}
