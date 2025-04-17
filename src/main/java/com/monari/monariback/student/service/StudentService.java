package com.monari.monariback.student.service;

import static com.monari.monariback.auth.enumerated.UserType.*;
import static com.monari.monariback.common.error.ErrorCode.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.monari.monariback.auth.entity.Accessor;
import com.monari.monariback.common.exception.NotFoundException;
import com.monari.monariback.common.service.ImageService;
import com.monari.monariback.student.dto.DownloadImageDto;
import com.monari.monariback.student.dto.StudentDto;
import com.monari.monariback.student.dto.request.StudentUpdateRequest;
import com.monari.monariback.student.entity.Student;
import com.monari.monariback.student.repository.StudentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentService {

	private final StudentRepository studentRepository;
	private final ImageService imageService;

	@Transactional(readOnly = true)
	public StudentDto findMyProfile(Accessor accessor) {
		return StudentDto.from(
				studentRepository.findByPublicId(accessor.getPublicId())
						.orElseThrow(() -> new NotFoundException(STUDENT_NOT_FOUND))
		);
	}

	@Transactional
	public StudentDto updateProfile(Accessor accessor, StudentUpdateRequest request) {
		Student student = studentRepository.findByPublicId(accessor.getPublicId())
				.orElseThrow(() -> new NotFoundException(STUDENT_NOT_FOUND));
		return StudentDto.from(
				student.updateProfile(
						request.schoolName(),
						request.schoolLevel(),
						request.grade(),
						request.profileImageUrl()
				)
		);
	}

	@Transactional
	public String updateProfileImage(Accessor accessor, MultipartFile file) {
		Student student = studentRepository.findByPublicId(accessor.getPublicId())
				.orElseThrow(() -> new NotFoundException(STUDENT_NOT_FOUND));

		String key = imageService.uploadProfileImage(
				STUDENT.toString(),
				accessor.getPublicId(),
				file
		);
		student.changeProfileImage(key);
		return key;
	}

	@Transactional(readOnly = true)
	public DownloadImageDto getProfileImage(Accessor accessor) {
		Student student = studentRepository.findByPublicId(accessor.getPublicId())
				.orElseThrow(() -> new NotFoundException(STUDENT_NOT_FOUND));
		String key = student.getProfileImageKeyOrThrow();
		return imageService.downloadFile(key);
	}
}
