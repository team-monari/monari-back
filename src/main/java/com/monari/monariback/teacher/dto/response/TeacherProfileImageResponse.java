package com.monari.monariback.teacher.dto.response;

import com.monari.monariback.common.dto.ProfileImageDto;

public record TeacherProfileImageResponse(
		String profileImageKey
) {
	public static TeacherProfileImageResponse from(ProfileImageDto dto) {
		return new TeacherProfileImageResponse(dto.profileImageKey());
	}
}
