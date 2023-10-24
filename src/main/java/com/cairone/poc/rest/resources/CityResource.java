package com.cairone.poc.rest.resources;

import com.cairone.poc.core.model.CityModel;
import com.cairone.poc.rest.ctrls.request.CityRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/cities")
public interface CityResource {

    @GetMapping("{id}")
    public ResponseEntity<CityModel> findById(@PathVariable Long id);

    @PostMapping
    public ResponseEntity<CityModel> create(@Valid @RequestBody CityRequest request);
}
