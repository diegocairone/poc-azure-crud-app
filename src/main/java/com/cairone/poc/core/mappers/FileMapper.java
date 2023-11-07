package com.cairone.poc.core.mappers;

import com.cairone.poc.core.model.FileModel;
import com.cairone.poc.data.entities.FileEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class FileMapper implements Converter<FileEntity, FileModel> {

    @Override
    public FileModel convert(FileEntity source) {
        return FileModel.builder()
                .id(source.getId())
                .originalFilename(source.getFileName())
                .sizeInBytes(source.getSizeInBytes())
                .contentType(source.getContentType())
                .build();
    }
}
