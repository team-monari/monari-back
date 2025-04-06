package com.monari.monariback.auth.dto.request;

public record OauthLoginRequest(
		String code,
		String socialProvider,
		String userType
) {
}
