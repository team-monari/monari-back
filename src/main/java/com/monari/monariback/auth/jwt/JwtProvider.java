package com.monari.monariback.auth.jwt;

import java.util.Date;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.monari.monariback.auth.enumerated.TokenType;
import com.monari.monariback.auth.enumerated.UserType;

import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtProvider {

	private final JwtProperties jwtProperties;

	public String createAccessToken(UUID publicId, UserType userType) {
		return createToken(
				publicId,
				jwtProperties.getAccessTokenExpirationMillis(),
				TokenType.ACCESS_TOKEN,
				userType
		);
	}

	// TODO: 추후 사용
	public String createRefreshToken(UUID publicId, UserType userType) {
		return createToken(
				publicId,
				jwtProperties.getAccessTokenExpirationMillis(),
				TokenType.REFRESH_TOKEN,
				userType
		);
	}

	private String createToken(
			UUID publicId,
			long expirationMillis,
			TokenType tokenType,
			UserType userType
	) {
		Date now = new Date();
		Date expiredDate = new Date(now.getTime() + expirationMillis);

		return Jwts.builder()
				.setSubject(publicId.toString())
				.claim(JwtProperties.USER_TYPE, userType)
				.setIssuedAt(now)
				.setExpiration(expiredDate)
				.claim(JwtProperties.TOKEN_TYPE, tokenType.name())
				.signWith(jwtProperties.getSecretKey())
				.compact();
	}
}
