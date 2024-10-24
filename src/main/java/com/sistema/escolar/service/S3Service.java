package com.sistema.escolar.service;

import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;
import software.amazon.awssdk.core.sync.RequestBody;

@Service
public class S3Service {

	@Autowired
	private S3Client s3Client;

	private static final String BUCKET_NAME = "noticias-projeto-extensao";

	public String uploadFile(String localFilePath, String fileName) throws IOException {
		try {
			// Constrói a requisição de upload
			PutObjectRequest putObjectRequest = PutObjectRequest.builder().bucket(BUCKET_NAME).key(fileName).build();

			// Faz o upload do arquivo
			s3Client.putObject(putObjectRequest, RequestBody.fromFile(Paths.get(localFilePath)));

			// Retorna a URL pública do arquivo
			return "https://" + BUCKET_NAME + ".s3.amazonaws.com/" + fileName;

		} catch (S3Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Erro ao fazer upload para o S3: " + e.getMessage());
		}
	}

	// Método para deletar um arquivo do S3
	public void deleteFile(String fileName) {
		try {
			DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder().bucket(BUCKET_NAME).key(fileName)
					.build();

			s3Client.deleteObject(deleteObjectRequest);

		} catch (S3Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Erro ao deletar arquivo do S3: " + e.getMessage());
		}
	}

	// Método para gerar o nome do arquivo único
	public String generateFileName(String originalFileName) {
		String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		String timestamp = LocalDateTime.now().format(formatter);
		String uuid = UUID.randomUUID().toString();
		return timestamp + "_" + uuid + extension;
	}
}
