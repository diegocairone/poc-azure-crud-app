package com.cairone.poc.core.services;

import com.azure.core.util.BinaryData;
import com.azure.core.util.Context;
import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.models.BlobHttpHeaders;
import com.azure.storage.blob.options.BlobParallelUploadOptions;
import com.cairone.poc.core.mappers.FileMapper;
import com.cairone.poc.core.model.FileModel;
import com.cairone.poc.data.entities.FileEntity;
import com.cairone.poc.data.repos.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.BufferedInputStream;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileService {

    // blobContainerClient bean represents an Azure Blob Storage container
    private final BlobContainerClient blobContainerClient;

    private final FileRepository fileRepository;

    private final FileMapper fileMapper;

    public Optional<FileModel> findMetadataById(long id) {
        return fileRepository.findById(id).map(fileMapper::convert);
    }

    public Optional<BufferedInputStream> findContentById(long id) {
        Optional<FileEntity> optional = fileRepository.findById(id);
        if (optional.isEmpty()) {
            return Optional.empty();
        }
        FileEntity fileEntity = optional.get();
        String blobName = String.format("%s.blob", fileEntity.getBlobId());
        BlobClient blobClient = blobContainerClient.getBlobClient(blobName);
        BinaryData data = blobClient.downloadContent();
        return Optional.ofNullable(new BufferedInputStream(data.toStream()));
    }

    public FileModel uploadFile(String fileName, String contentType, long sizeInBytes, BufferedInputStream inputStream) {

        UUID blobId = UUID.randomUUID();
        Long fileId = fileRepository.getLastId().orElse(0L) + 1L;

        FileEntity fileEntity = new FileEntity();
        fileEntity.setId(fileId);
        fileEntity.setFileName(fileName);
        fileEntity.setContentType(contentType);
        fileEntity.setSizeInBytes(sizeInBytes);
        fileEntity.setBlobId(blobId);

        fileRepository.save(fileEntity);

        // Blob index tags
        Map<String, String> tags = new HashMap<>();
        tags.put("fileName", fileName);

        // This bean represents an Azure Blob Storage blob
        BlobClient blobClient = blobContainerClient.getBlobClient(
                String.format("%s.blob", blobId));

        BlobHttpHeaders jsonHeaders = new BlobHttpHeaders()
                .setContentType(contentType);

        BlobParallelUploadOptions options = new BlobParallelUploadOptions(inputStream)
                .setTags(tags)
                .setHeaders(jsonHeaders);

        blobClient.uploadWithResponse(options, Context.NONE);

        return fileMapper.convert(fileEntity);
    }
}
