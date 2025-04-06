package com.monari.monariback.auth.oauth.userinfo;

import com.monari.monariback.auth.dto.response.KakaoUserInfoResponse;

public class KakaoUserInfo implements OauthUserInfo {

	private final String socialId;
	private final String email;
	private final String nickName;

	public KakaoUserInfo(KakaoUserInfoResponse response) {
		this.socialId = String.valueOf(response.id());
		this.email = response.kakaoAccount().email();
		this.nickName = response.kakaoAccount().profile().nickname();
	}

	@Override
	public String getSocialId() {
		return socialId;
	}

	@Override
	public String getEmail() {
		return email;
	}

	@Override
	public String getNickName() {
		return nickName;
	}
}
