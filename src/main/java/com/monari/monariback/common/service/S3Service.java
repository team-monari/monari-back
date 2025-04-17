package com.monari.monariback.common.service;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.core.exception.SdkException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Slf4j
@Service
@RequiredArgsConstructor
public class S3Service {

	private final S3Client s3Client;

	@Value("${aws.s3.bucket}")
	private String bucketName;

	public void uploadFile(String key, byte[] data, String contentType) {
		try {
			PutObjectRequest putReq = PutObjectRequest.builder()
					.bucket(bucketName)
					.key(key)
					.contentType(contentType)
					.build();
			s3Client.putObject(putReq, RequestBody.fromBytes(data));
		} catch (SdkException e) {
			log.error(e.getMessage());
			throw new RuntimeException("Failed to upload file to S3");
		}
	}

	public String uploadProfileImage(String folder, UUID publicId, MultipartFile file) {
		String key = generateKey(folder, publicId, file.getOriginalFilename());
		try {
			byte[] data = file.getBytes();
			String contentType = file.getContentType();
			uploadFile(key, data, contentType);
			return key;
		} catch (IOException e) {
			throw new RuntimeException("Failed to read bytes from uploaded file");
		}
	}

	/**
	 * S3에서 파일을 다운로드하여 byte[]로 반환한다.
	 *
	 * @param key S3 객체 key
	 * @return 파일 내용을 담은 byte[]
	 * @throws RuntimeException 다운로드 실패 시 발생
	 */
	public byte[] downloadFile(String key) {
		try {
			GetObjectRequest getReq = GetObjectRequest.builder()
					.bucket(bucketName)
					.key(key)
					.build();

			ResponseBytes<GetObjectResponse> s3ObjectBytes = s3Client.getObjectAsBytes(
					getReq);
			return s3ObjectBytes.asByteArray();
		} catch (SdkException e) {
			throw new RuntimeException("Failed to download file from S3");
		}
	}

	private String generateKey(String folder, UUID publicId, String originalFilename) {
		return folder + "/" + publicId + "-" + originalFilename;
	}
}
