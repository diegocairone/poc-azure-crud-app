package com.cairone.poc.rest.ctrls;

import com.cairone.poc.core.model.CityModel;
import com.cairone.poc.core.services.CityService;
import com.cairone.poc.rest.ctrls.request.CityRequest;
import com.cairone.poc.rest.resources.CityResource;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

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

    @Override
    public ResponseEntity<CityModel> create(CityRequest request) {
        CityModel cityModel = cityService.create(request);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(cityModel.getId()).toUri();
        return ResponseEntity.created(location).body(cityModel);
    }
}
