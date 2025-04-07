package com.monari.monariback.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.monari.monariback.auth.dto.request.OauthLoginRequest;
import com.monari.monariback.auth.dto.response.OauthLoginResponse;
import com.monari.monariback.auth.service.AuthService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

	private final AuthService authService;

	@PostMapping("/oauth")
	public ResponseEntity<OauthLoginResponse> oauthLogin(
			@RequestBody OauthLoginRequest request
	) {
		return ResponseEntity.ok().body(
				OauthLoginResponse.of(authService.login(request))
		);
	}
}
