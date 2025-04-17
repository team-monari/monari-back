package com.monari.monariback.student.dto.response;

public record StudentProfileImageResponse(
		byte[] profileImage
) {
	public static StudentProfileImageResponse from(byte[] profileImage) {
		return new StudentProfileImageResponse(profileImage);
	}
}
