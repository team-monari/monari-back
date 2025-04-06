package com.monari.monariback.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.monari.monariback.auth.dto.request.OauthLoginRequest;
import com.monari.monariback.auth.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

	private final AuthService authService;

	@PostMapping("/oauth")
	public ResponseEntity<?> oauthLogin(@RequestBody OauthLoginRequest request) {
		authService.login(request);
		return ResponseEntity.ok().build();
	}
}
