package com.monari.monariback.common.dto;

public record ProfileImageDto(
		String profileImageKey
) {
	public static ProfileImageDto from(String profileImageKey) {
		return new ProfileImageDto(profileImageKey);
	}
}
