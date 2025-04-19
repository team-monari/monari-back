package com.monari.monariback.student.dto.response;

import com.monari.monariback.common.dto.ProfileImageDto;

public record StudentProfileImageResponse(
		String profileImageKey
) {
	public static StudentProfileImageResponse from(ProfileImageDto dto) {
		return new StudentProfileImageResponse(dto.profileImageKey());
	}
}
