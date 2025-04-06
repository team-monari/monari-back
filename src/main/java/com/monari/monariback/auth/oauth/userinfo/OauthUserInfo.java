package com.monari.monariback.auth.oauth.userinfo;

public interface OauthUserInfo {
	String getSocialId();

	String getEmail();

	String getNickName();
}
