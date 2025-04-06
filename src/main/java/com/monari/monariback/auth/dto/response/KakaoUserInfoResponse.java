package com.monari.monariback.auth.dto.response;

public record KakaoUserInfoResponse(
		Long id,
		KakaoAccount kakaoAccount
) {
	public record KakaoAccount(
			String email,
			Profile profile
	) {
		public record Profile(
				String nickname
		) {
		}
	}
}
