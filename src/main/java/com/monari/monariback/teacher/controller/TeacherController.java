package com.monari.monariback.teacher.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.monari.monariback.auth.aop.Auth;
import com.monari.monariback.auth.aop.OnlyTeacher;
import com.monari.monariback.auth.entity.Accessor;
import com.monari.monariback.teacher.dto.response.TeacherResponse;
import com.monari.monariback.teacher.service.TeacherService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/teachers")
public class TeacherController {

	private final TeacherService teacherService;

	@OnlyTeacher
	@GetMapping("/me")
	public ResponseEntity<TeacherResponse> getMyProfile(@Auth Accessor accessor) {
		return ResponseEntity.ok().body(
				TeacherResponse.from(
						teacherService.findMyProfile(accessor)
				)
		);
	}
}
