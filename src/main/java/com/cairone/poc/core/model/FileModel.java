package com.cairone.poc.core.model;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class FileModel {

    private final long id;
    private final String originalFilename;

    private final long sizeInBytes;

    private final String contentType;
}
