package com.monari.monariback.auth.dto;

public record AuthTokenDto(
		String accessToken
) {
	public static AuthTokenDto of(String accessToken) {
		return new AuthTokenDto(accessToken);
	}
}
