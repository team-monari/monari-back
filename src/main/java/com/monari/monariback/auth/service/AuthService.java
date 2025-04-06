package com.monari.monariback.auth.service;

import org.springframework.stereotype.Service;

import com.monari.monariback.auth.dto.request.OauthLoginRequest;
import com.monari.monariback.auth.oauth.OauthProvider;
import com.monari.monariback.auth.oauth.OauthProviders;
import com.monari.monariback.auth.oauth.userinfo.OauthUserInfo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

	private final OauthProviders oauthProviders;

	public void login(OauthLoginRequest request) {
		OauthProvider provider = oauthProviders.getProvider(request.socialProvider());
		String accessToken = provider.getAccessToken(request.code());
		OauthUserInfo userInfo = provider.getUserInfo(accessToken);

		log.info("소셜ID: {}", userInfo.getSocialId());
		log.info("email: {}", userInfo.getEmail());
		log.info("NickName: {}", userInfo.getNickName());
		// userType (student/teacher)에 따라 분기 후 로그인/회원가입
		handleLogin(userInfo, request.userType());
	}

	private void handleLogin(OauthUserInfo userInfo, String userType) {
		// UserService 등과 연계
	}
}
