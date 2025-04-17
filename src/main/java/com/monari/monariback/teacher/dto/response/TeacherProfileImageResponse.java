package com.monari.monariback.teacher.dto.response;

public record TeacherProfileImageResponse(
		String profileImageKey
) {
	public static TeacherProfileImageResponse from(String profileImageKey) {
		return new TeacherProfileImageResponse(profileImageKey);
	}
}
