package com.cairone.poc.data.repos;

import com.cairone.poc.data.entities.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CityRepository extends JpaRepository<CityEntity, Long> {

    @Query("SELECT MAX(e.id) FROM CityEntity e")
    Optional<Long> getLastId();
}
