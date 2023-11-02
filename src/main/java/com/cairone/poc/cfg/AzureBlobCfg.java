package com.cairone.poc.cfg;

import com.azure.identity.DefaultAzureCredential;
import com.azure.identity.DefaultAzureCredentialBuilder;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AzureBlobCfg {

    /* This bean represents an Azure Storage Account
     */
    @Bean
    public BlobServiceClient getBlobServiceClient() {

        DefaultAzureCredential credential = new DefaultAzureCredentialBuilder()
                .build();

        return new BlobServiceClientBuilder()
                .credential(credential)
                .endpoint("https://pocpasswordlesssc.blob.core.windows.net")
                .buildClient();
    }

    /* This bean represents an Azure Blob Storage container
     */
    @Bean
    public BlobContainerClient getBlobContainerClient(BlobServiceClient blobServiceClient) {
        return blobServiceClient.getBlobContainerClient("mycontainer");
    }
}
