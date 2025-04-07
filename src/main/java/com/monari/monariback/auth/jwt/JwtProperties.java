package com.monari.monariback.auth.jwt;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.security.Keys;
import lombok.Getter;

@Getter
@Component
public class JwtProperties {

	private final String secretKey;
	private final long accessTokenExpirationMillis;

	public JwtProperties(
			@Value("${jwt.secret-key}") final String secretKey,
			@Value("${jwt.accessToken-expiration-millis}") final long accessTokenExpirationMillis
	) {
		this.secretKey = secretKey;
		this.accessTokenExpirationMillis = accessTokenExpirationMillis;
	}

	public SecretKey getSecretKey() {
		return Keys.hmacShaKeyFor(secretKey.getBytes());
	}
}
