package com.monari.monariback.auth.oauth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import com.monari.monariback.auth.dto.response.OauthAccessTokenResponse;
import com.monari.monariback.auth.oauth.userinfo.OauthUserInfo;
import com.monari.monariback.common.enumerated.SocialProvider;

import reactor.core.publisher.Mono;

@Component
public class KakaoOauthProvider implements OauthProvider {

	private final WebClient webClient;
	private final String clientId;
	private final String redirectUri;
	private final String clientSecret;
	private final String tokenUri;

	public KakaoOauthProvider(
			WebClient webClient,
			@Value("${oauth.kakao.client-id}") String clientId,
			@Value("${oauth.kakao.redirect-uri}") String redirectUri,
			@Value("${oauth.kakao.client-secret:}") String clientSecret,
			@Value("${oauth.kakao.token-uri}") String tokenUri
	) {
		this.webClient = webClient;
		this.clientId = clientId;
		this.redirectUri = redirectUri;
		this.clientSecret = clientSecret;
		this.tokenUri = tokenUri;
	}

	@Override
	public SocialProvider getProvider() {
		return SocialProvider.KAKAO;
	}

	@Override
	public String getAccessToken(String code) {
		MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
		formData.add("grant_type", "authorization_code");
		formData.add("client_id", clientId);
		formData.add("redirect_uri", redirectUri);
		formData.add("code", code);

		if (!clientSecret.isBlank()) {
			formData.add("client_secret", clientSecret);
		}

		return webClient.post()
				.uri(tokenUri)
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.body(BodyInserters.fromFormData(formData))
				.retrieve()
				.onStatus(
						status -> status.is4xxClientError() || status.is5xxServerError(),
						this::handleOauthError
				)
				.bodyToMono(OauthAccessTokenResponse.class)
				.map(OauthAccessTokenResponse::accessToken)
				.block(); // 동기 방식으로 반
	}

	@Override
	public OauthUserInfo getUserInfo(String accessToken) {
		return null;
	}

	private Mono<? extends Throwable> handleOauthError(ClientResponse response) {
		return response.bodyToMono(String.class)
				.flatMap(errorBody ->
						Mono.error(new RuntimeException("카카오 토큰 요청 실패: " + errorBody))
				);
	}
}
