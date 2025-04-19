package com.monari.monariback.teacher.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.monari.monariback.auth.aop.Auth;
import com.monari.monariback.auth.aop.OnlyTeacher;
import com.monari.monariback.auth.entity.Accessor;
import com.monari.monariback.common.dto.DownloadImageDto;
import com.monari.monariback.teacher.dto.request.TeacherUpdateRequest;
import com.monari.monariback.teacher.dto.response.TeacherProfileImageResponse;
import com.monari.monariback.teacher.dto.response.TeacherResponse;
import com.monari.monariback.teacher.service.TeacherService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/teachers")
public class TeacherController {

	private final TeacherService teacherService;

	@GetMapping("/{publicId}")
	public ResponseEntity<TeacherResponse> getTeacher(@PathVariable UUID publicId) {
		return ResponseEntity.ok().body(
				TeacherResponse.from(
						teacherService.getTeacher(publicId)
				)
		);
	}

	@OnlyTeacher
	@GetMapping("/me")
	public ResponseEntity<TeacherResponse> getMyProfile(@Auth Accessor accessor) {
		return ResponseEntity.ok().body(
				TeacherResponse.from(
						teacherService.findMyProfile(accessor)
				)
		);
	}

	@OnlyTeacher
	@PatchMapping("/me")
	public ResponseEntity<TeacherResponse> updateProfile(
			@Auth Accessor accessor,
			@RequestBody @Valid TeacherUpdateRequest request
	) {
		return ResponseEntity.ok().body(
				TeacherResponse.from(
						teacherService.updateProfile(accessor, request)
				)
		);
	}

	@OnlyTeacher
	@PatchMapping("/me/profile-image")
	public ResponseEntity<TeacherProfileImageResponse> updateProfileImage(
			@Auth Accessor accessor,
			@RequestPart("file") MultipartFile file
	) {
		return ResponseEntity.ok().body(
				TeacherProfileImageResponse.from(
						teacherService.updateProfileImage(accessor, file)
				)
		);
	}

	@GetMapping("/{publicId}/profile-image")
	public ResponseEntity<byte[]> getProfileImage(@PathVariable UUID publicId) {
		DownloadImageDto profileImage = teacherService.getProfileImage(publicId);
		return ResponseEntity.ok()
				.contentType(profileImage.contentType())
				.body(profileImage.data());
	}
}
