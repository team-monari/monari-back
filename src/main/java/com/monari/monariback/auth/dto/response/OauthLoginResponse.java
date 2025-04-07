package com.monari.monariback.auth.dto.response;

import com.monari.monariback.auth.dto.AuthTokenDto;

public record OauthLoginResponse(
		String accessToken
) {
	public static OauthLoginResponse of(AuthTokenDto dto) {
		return new OauthLoginResponse(dto.accessToken());
	}
}
