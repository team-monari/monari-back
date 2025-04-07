package com.monari.monariback.auth.oauth;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.monari.monariback.common.enumerated.SocialProvider;

@Component
public class OauthProviders {

	private final Map<SocialProvider, OauthProvider> providerMap;

	public OauthProviders(List<OauthProvider> providers) {
		this.providerMap = providers.stream()
				.collect(Collectors.toMap(
						OauthProvider::getProvider,
						Function.identity()
				));
	}

	public OauthProvider getProvider(SocialProvider provider) {
		OauthProvider found = providerMap.get(provider);
		if (found == null) {
			throw new IllegalArgumentException("지원하지 않는 소셜 로그인 플랫폼입니다: " + provider);
		}
		return found;
	}
}
