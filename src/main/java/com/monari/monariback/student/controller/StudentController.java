package com.monari.monariback.student.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.monari.monariback.auth.aop.Auth;
import com.monari.monariback.auth.aop.OnlyStudent;
import com.monari.monariback.auth.entity.Accessor;
import com.monari.monariback.student.dto.DownloadImageDto;
import com.monari.monariback.student.dto.request.StudentUpdateRequest;
import com.monari.monariback.student.dto.response.StudentProfileImageResponse;
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

	@OnlyStudent
	@PatchMapping(value = "/me/profile-image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<StudentProfileImageResponse> updateProfileImage(
			@Auth Accessor accessor,
			@RequestPart("file") MultipartFile file
	) {
		return ResponseEntity.ok().body(
				StudentProfileImageResponse.from(
						studentService.updateProfileImage(accessor, file)
				)
		);
	}

	@OnlyStudent
	@GetMapping("/me/profile-image")
	public ResponseEntity<byte[]> getProfileImage(
			@Auth Accessor accessor
	) {
		DownloadImageDto profileImage = studentService.getProfileImage(accessor);
		return ResponseEntity.ok()
				.contentType(profileImage.contentType())
				.body(profileImage.data());
	}
}
