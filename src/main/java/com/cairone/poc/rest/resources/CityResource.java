package com.cairone.poc.rest.resources;

import com.cairone.poc.core.model.CityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/cities")
public interface CityResource {

    @GetMapping("{id}")
    public ResponseEntity<CityModel> findById(@PathVariable Long id);
}
