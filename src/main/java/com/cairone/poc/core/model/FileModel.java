package com.cairone.poc.core.model;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class FileModel {

    private final UUID id;
    private final String originalFilename;
}
