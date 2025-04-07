package com.monari.monariback.auth.oauth;

import com.monari.monariback.auth.oauth.userinfo.OauthUserInfo;
import com.monari.monariback.common.enumerated.SocialProvider;

public interface OauthProvider {
	SocialProvider getProvider();

	String getAccessToken(String code);

	OauthUserInfo getUserInfo(String accessToken);
}
