package com.monari.monariback.student.dto.response;

public record StudentProfileImageResponse(
		String profileImageKey
) {
	public static StudentProfileImageResponse from(String profileImageKey) {
		return new StudentProfileImageResponse(profileImageKey);
	}
}
