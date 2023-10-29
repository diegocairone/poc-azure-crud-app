package com.cairone.poc.rest.resources;

import com.cairone.poc.core.model.SecretModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/secrets")
public interface SecretResource {

    @GetMapping("{id}")
    ResponseEntity<SecretModel> getSecretById(@PathVariable("id") String id);

}
