package com.monari.monariback.auth.jwt;

import static com.monari.monariback.auth.constant.JwtConstants.*;
import static com.monari.monariback.global.config.error.ErrorCode.*;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.monari.monariback.auth.enumerated.TokenType;
import com.monari.monariback.auth.enumerated.UserType;
import com.monari.monariback.global.config.error.exception.AuthException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtResolver {

	private final JwtProperties jwtProperties;

	public boolean isValid(String token) {
		try {
			parseClaims(token);
			return true;
		} catch (JwtException | IllegalArgumentException e) {
			return false;
		}
	}

	public UUID extractPublicId(String token) {
		return UUID.fromString(parseClaims(token).getSubject());
	}

	public UserType extractUserType(String token) {
		String value = parseClaims(token).get(USER_TYPE, String.class);
		return UserType.valueOf(value);
	}

	// TODO : 추후 사용
	public TokenType extractTokenType(String token) {
		String value = parseClaims(token).get(TOKEN_TYPE, String.class);
		return TokenType.valueOf(value);
	}

	private Claims parseClaims(String token) {
		try {
			return Jwts.parserBuilder()
					.setSigningKey(jwtProperties.getSecretKey())
					.build()
					.parseClaimsJws(token)
					.getBody();
		} catch (ExpiredJwtException e) {
			throw new AuthException(AUTH_TOKEN_EXPIRED);
		} catch (JwtException | IllegalArgumentException e) {
			throw new AuthException(AUTH_TOKEN_INVALID);
		}
	}
}
