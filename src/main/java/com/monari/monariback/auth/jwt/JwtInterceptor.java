package com.monari.monariback.auth.jwt;

import static com.monari.monariback.auth.constant.JwtConstants.*;

import java.util.UUID;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.monari.monariback.auth.enumerated.UserType;
import com.monari.monariback.global.config.error.ErrorCode;
import com.monari.monariback.global.config.error.exception.AuthException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtInterceptor implements HandlerInterceptor {

	private final JwtResolver jwtResolver;

	@Override
	public boolean preHandle(
			@NonNull HttpServletRequest request,
			@NonNull HttpServletResponse response,
			@NonNull Object handler
	) throws Exception {

		String token = extractToken(request);

		if (token == null || !jwtResolver.isValid(token)) {
			throw new AuthException(ErrorCode.AUTH_TOKEN_INVALID);
		}

		setUserAttributes(request, token);
		return true;
	}

	private String extractToken(HttpServletRequest request) {
		String header = request.getHeader(AUTHORIZATION_HEADER);
		if (header == null || !header.startsWith(BEARER_PREFIX)) {
			return null;
		}
		return header.substring(BEARER_PREFIX_LENGTH);
	}

	private void setUserAttributes(HttpServletRequest request, String token) {
		UUID publicId = jwtResolver.extractPublicId(token);
		UserType userType = jwtResolver.extractUserType(token);

		request.setAttribute(REQUEST_ATTR_PUBLIC_ID, publicId);
		request.setAttribute(REQUEST_ATTR_USER_TYPE, userType);
	}
}
