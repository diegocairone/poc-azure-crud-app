package com.cairone.poc.data.repos;

import com.cairone.poc.data.entities.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<CityEntity, Long> {
}
