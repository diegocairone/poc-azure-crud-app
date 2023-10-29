package com.cairone.poc.core.services;

import com.azure.security.keyvault.secrets.SecretClient;
import com.azure.security.keyvault.secrets.models.KeyVaultSecret;
import com.cairone.poc.core.model.SecretModel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SecretService {

    private final SecretClient secretClient;

    /*
    public SecretService(SecretClient secretClient) {
        this.sc = new SecretClientBuilder()
                .vaultUrl("https://poc-passwordless-vault.vault.azure.net/")
                .credential(new DefaultAzureCredentialBuilder().build())
                .buildClient();
    }
    */

    public Optional<SecretModel> findByName(String name) {
        KeyVaultSecret secret = secretClient.getSecret(name);
        SecretModel secretModel = SecretModel.builder()
                .withId(secret.getId())
                .withName(secret.getName())
                .withValue(secret.getValue())
                .build();
        return Optional.of(secretModel);
    }
}
