package com.monari.monariback.auth.oauth;

import static com.monari.monariback.auth.constant.KakaoOauthConstants.*;
import static com.monari.monariback.common.error.ErrorCode.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import com.monari.monariback.auth.dto.response.KakaoAccessTokenResponse;
import com.monari.monariback.auth.dto.response.KakaoUserInfoResponse;
import com.monari.monariback.auth.oauth.userinfo.KakaoUserInfo;
import com.monari.monariback.auth.oauth.userinfo.OauthUserInfo;
import com.monari.monariback.common.enumerated.SocialProvider;
import com.monari.monariback.common.exception.AuthException;

import reactor.core.publisher.Mono;

@Component
public class KakaoOauthProvider implements OauthProvider {

	private final WebClient webClient;
	private final String clientId;
	private final String redirectUri;
	private final String tokenUri;
	private final String userInfoUri;

	public KakaoOauthProvider(
			WebClient webClient,
			@Value("${oauth.kakao.client-id}") String clientId,
			@Value("${oauth.kakao.redirect-uri}") String redirectUri,
			@Value("${oauth.kakao.token-uri}") String tokenUri,
			@Value("${oauth.kakao.user-info-uri}") String userInfoUri
	) {
		this.webClient = webClient;
		this.clientId = clientId;
		this.redirectUri = redirectUri;
		this.tokenUri = tokenUri;
		this.userInfoUri = userInfoUri;
	}

	@Override
	public SocialProvider getProvider() {
		return SocialProvider.KAKAO;
	}

	@Override
	public String getAccessToken(String code) {
		MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
		formData.add(GRANT_TYPE_KEY, GRANT_TYPE);
		formData.add(CLIENT_ID_KEY, clientId);
		formData.add(REDIRECT_URI_KEY, redirectUri);
		formData.add(CODE_KEY, code);

		return webClient.post()
				.uri(tokenUri)
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.body(BodyInserters.fromFormData(formData))
				.retrieve()
				.onStatus(
						HttpStatusCode::isError,
						this::handleOauthError
				)
				.bodyToMono(KakaoAccessTokenResponse.class)
				.map(KakaoAccessTokenResponse::accessToken)
				.block();
	}

	@Override
	public OauthUserInfo getUserInfo(String accessToken) {
		KakaoUserInfoResponse response = fetchUserInfo(accessToken);
		return new KakaoUserInfo(response);
	}

	private KakaoUserInfoResponse fetchUserInfo(String accessToken) {
		return webClient
				.post()
				.uri(userInfoUri)
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.header(HttpHeaders.AUTHORIZATION, BEARER_PREFIX + accessToken)
				.retrieve()
				.onStatus(
						HttpStatusCode::isError,
						this::handleOauthError
				)
				.bodyToMono(KakaoUserInfoResponse.class)
				.blockOptional()
				.orElseThrow(() -> new AuthException(OAUTH_USERINFO_RESPONSE_EMPTY));
	}

	private Mono<? extends Throwable> handleOauthError(ClientResponse response) {
		return response.bodyToMono(String.class)
				.flatMap(errorBody ->
						Mono.error(new AuthException(OAUTH_TOKEN_REQUEST_FAILED))
				);
	}
}
