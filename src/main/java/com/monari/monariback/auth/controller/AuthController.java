package com.monari.monariback.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.monari.monariback.auth.dto.request.OauthLoginRequest;
import com.monari.monariback.auth.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

	private final AuthService authService;

	@PostMapping("/oauth")
	public ResponseEntity<?> oauthLogin(@RequestBody OauthLoginRequest request) {
		return ResponseEntity.ok().build();
	}
}
