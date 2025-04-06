package com.monari.monariback.auth.dto.request;

import com.monari.monariback.common.enumerated.SocialProvider;

public record OauthLoginRequest(
		String code,
		SocialProvider socialProvider,
		String userType
) {
}
