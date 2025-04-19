package com.monari.monariback.common.service;

import static com.monari.monariback.common.constant.S3GenerateKeyConstant.*;
import static com.monari.monariback.common.error.ErrorCode.*;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.monari.monariback.common.dto.DownloadImageDto;
import com.monari.monariback.common.enumerated.ImageExtension;
import com.monari.monariback.common.exception.BusinessException;

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
public class ImageService {

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
			throw new BusinessException(IMAGE_UPLOAD_FAILED);
		}
	}

	public String uploadProfileImage(String folder, UUID publicId, MultipartFile file) {
		validateImageFile(file);
		String key = generateKey(folder, publicId, file.getOriginalFilename());
		try {
			byte[] data = file.getBytes();
			String contentType = file.getContentType();
			uploadFile(key, data, contentType);
			return key;
		} catch (IOException e) {
			throw new BusinessException(IMAGE_FILE_READ_FAILED);
		}
	}

	public DownloadImageDto downloadFile(String key) {
		try {
			ResponseBytes<GetObjectResponse> respBytes =
					s3Client.getObjectAsBytes(GetObjectRequest.builder()
							.bucket(bucketName)
							.key(key)
							.build());
			MediaType mediaType = deriveMediaTypeFromKey(key);
			return DownloadImageDto.from(
					respBytes.asByteArray(), mediaType);
		} catch (SdkException e) {
			throw new BusinessException(IMAGE_DOWNLOAD_FAILED);
		}
	}

	private void validateImageFile(MultipartFile file) {
		String originalFilename = file.getOriginalFilename();
		if (originalFilename == null || originalFilename.isBlank()) {
			throw new BusinessException(IMAGE_FILE_INVALID_NAME);
		}

		String ext = extractExtension(originalFilename);
		if (!ImageExtension.isSupported(ext)) {
			throw new BusinessException(IMAGE_INVALID_FORMAT);
		}
	}

	private MediaType deriveMediaTypeFromKey(String key) {
		String ext = extractExtension(key);
		return ImageExtension.mediaTypeFor(ext);
	}

	private String extractExtension(String filename) {
		int idx = filename.lastIndexOf('.');
		if (idx < 0 || idx == filename.length() - 1) {
			throw new BusinessException(IMAGE_INVALID_FORMAT);
		}
		return filename.substring(idx + 1).toLowerCase();
	}

	private String generateKey(String folder, UUID publicId, String originalFilename) {
		return folder + FOLDER_SEPARATOR + publicId + FILENAME_SEPARATOR + originalFilename;
	}
}
