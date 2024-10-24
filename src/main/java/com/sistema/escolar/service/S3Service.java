package com.sistema.escolar.service;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

@Service
public class S3Service {

	@Autowired
	private S3Client s3Client;

	private final String BUCKET_NAME = "noticias-projeto-extensao";

	public String uploadFile(String filePath, String fileName) {
		File file = new File(filePath);

		PutObjectRequest request = PutObjectRequest.builder().bucket(BUCKET_NAME).key(fileName).build();

		PutObjectResponse response = s3Client.putObject(request, file.toPath());

		// Aqui você pode construir a URL pública ou gerada para a imagem
		return "https://" + BUCKET_NAME + ".s3.amazonaws.com/" + fileName;
	}
}
