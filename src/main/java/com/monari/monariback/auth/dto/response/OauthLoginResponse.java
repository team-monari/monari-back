package com.monari.monariback.auth.dto.response;

import com.monari.monariback.auth.dto.AuthTokenDto;
import com.monari.monariback.auth.enumerated.UserType;

public record OauthLoginResponse(
		String accessToken,
		UserType userType
) {
	public static OauthLoginResponse of(AuthTokenDto dto, UserType userType) {
		return new OauthLoginResponse(dto.accessToken(), userType);
	}
}
