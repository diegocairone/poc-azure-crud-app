package com.cairone.poc.rest.ctrls;

import com.cairone.poc.core.model.SecretModel;
import com.cairone.poc.core.services.SecretService;
import com.cairone.poc.rest.resources.SecretResource;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SecretCtrl implements SecretResource {

    private final SecretService secretService;

    @Override
    public ResponseEntity<SecretModel> getSecretById(String id) {
        return ResponseEntity.of(secretService.findByName(id));
    }
}
