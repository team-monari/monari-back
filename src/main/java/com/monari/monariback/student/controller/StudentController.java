package com.monari.monariback.student.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.monari.monariback.auth.aop.Auth;
import com.monari.monariback.auth.aop.OnlyStudent;
import com.monari.monariback.auth.entity.Accessor;
import com.monari.monariback.student.dto.request.StudentUpdateRequest;
import com.monari.monariback.student.dto.response.StudentResponse;
import com.monari.monariback.student.service.StudentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/students")
public class StudentController {

	private final StudentService studentService;

	@OnlyStudent
	@GetMapping("/me")
	public ResponseEntity<StudentResponse> getMyProfile(@Auth Accessor accessor) {
		return ResponseEntity.ok().body(
				StudentResponse.from(
						studentService.findMyProfile(accessor)
				)
		);
	}

	@OnlyStudent
	@PatchMapping("/me")
	public ResponseEntity<StudentResponse> updateProfile(
			@Auth Accessor accessor,
			@RequestBody @Valid StudentUpdateRequest request
	) {
		return ResponseEntity.ok().body(
				StudentResponse.from(
						studentService.updateProfile(accessor, request)
				)
		);
	}
}
