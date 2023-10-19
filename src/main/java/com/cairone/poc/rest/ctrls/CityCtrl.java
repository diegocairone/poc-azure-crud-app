package com.cairone.poc.rest.ctrls;

import com.cairone.poc.core.model.CityModel;
import com.cairone.poc.core.services.CityService;
import com.cairone.poc.rest.resources.CityResource;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CityCtrl implements CityResource {

    private final CityService cityService;

    @Override
    public ResponseEntity<CityModel> findById(Long id) {
        return cityService.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new RuntimeException("Resource with the given ID was not found"));
    }
}
