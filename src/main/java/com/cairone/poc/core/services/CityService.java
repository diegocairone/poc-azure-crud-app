package com.cairone.poc.core.services;

import com.cairone.poc.core.mappers.CityMapper;
import com.cairone.poc.core.model.CityModel;
import com.cairone.poc.data.repos.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CityService {

    private final CityRepository cityRepository;

    private final CityMapper cityMapper;

    @Transactional(readOnly = true)
    public Optional<CityModel> findById(Long id) {
        return cityRepository.findById(id).map(cityMapper::convert);
    }
}
