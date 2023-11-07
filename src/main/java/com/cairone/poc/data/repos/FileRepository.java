package com.cairone.poc.data.repos;

import com.cairone.poc.data.entities.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface FileRepository extends JpaRepository<FileEntity, Long> {

    @Query("SELECT MAX(e.id) FROM FileEntity e")
    Optional<Long> getLastId();
}
