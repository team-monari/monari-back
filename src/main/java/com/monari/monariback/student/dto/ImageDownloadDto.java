package com.monari.monariback.student.dto;

public record ImageDownloadDto(
		String contentType,
		byte[] data
) {
	public static ImageDownloadDto from(String contentType, byte[] data) {
		return new ImageDownloadDto(contentType, data);
	}
}
