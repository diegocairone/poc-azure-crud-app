package com.cairone.poc;

import com.azure.core.http.rest.PagedIterable;
import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.models.TaggedBlobItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication()
public class App implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		/*
		// blobContainerClient bean represents an Azure Blob Storage container
		PagedIterable<TaggedBlobItem> items = blobContainerClient.findBlobsByTags("\"uuid\"='26f847ec-0352-4d61-9e9d-5eb4ab0ca52b'");

		String name = items.stream().findFirst().map(e -> e.getName())
				.orElseThrow(() -> new RuntimeException("Does not exist!"));

		// This bean represents an Azure Blob Storage blob
		BlobClient blob = blobContainerClient.getBlobClient(name);

		String content = blob.downloadContent().toString();
		log.info(content);
		 */
	}

	//@Autowired
	private BlobContainerClient blobContainerClient;
}
