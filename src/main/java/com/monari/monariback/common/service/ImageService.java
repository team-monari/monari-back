package com.monari.monariback.common.service;

import static com.monari.monariback.common.constant.MediaTypeConstant.*;
import static com.monari.monariback.common.constant.S3GenerateKeyConstant.*;
import static com.monari.monariback.common.error.ErrorCode.*;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.monari.monariback.common.exception.BusinessException;
import com.monari.monariback.common.dto.DownloadImageDto;

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

	private MediaType deriveMediaTypeFromKey(String key) {
		int idx = key.lastIndexOf('.');
		if (idx == -1 || idx == key.length() - 1) {
			return DEFAULT_MEDIA_TYPE;
		}
		String ext = key.substring(idx + 1).toLowerCase();
		return switch (ext) {
			case EXT_PNG -> MediaType.IMAGE_PNG;
			case EXT_JPG, EXT_JPEG -> MediaType.IMAGE_JPEG;
			case EXT_GIF -> MediaType.IMAGE_GIF;
			case EXT_BMP -> MEDIA_TYPE_BMP;
			case EXT_WEBP -> MEDIA_TYPE_WEBP;
			default -> MediaType.APPLICATION_OCTET_STREAM;
		};
	}

	private String generateKey(String folder, UUID publicId, String originalFilename) {
		return folder + FOLDER_SEPARATOR + publicId + FILENAME_SEPARATOR + originalFilename;
	}
}
