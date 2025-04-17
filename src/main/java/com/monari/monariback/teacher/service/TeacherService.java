package com.monari.monariback.teacher.service;

import static com.monari.monariback.auth.enumerated.UserType.*;
import static com.monari.monariback.common.error.ErrorCode.*;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.monari.monariback.auth.entity.Accessor;
import com.monari.monariback.common.dto.ProfileImageDto;
import com.monari.monariback.common.exception.NotFoundException;
import com.monari.monariback.common.service.ImageService;
import com.monari.monariback.teacher.dto.TeacherDto;
import com.monari.monariback.teacher.dto.request.TeacherUpdateRequest;
import com.monari.monariback.teacher.entity.Teacher;
import com.monari.monariback.teacher.repository.TeacherRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TeacherService {

	private final TeacherRepository teacherRepository;
	private final ImageService imageService;

	@Transactional(readOnly = true)
	public TeacherDto findMyProfile(Accessor accessor) {
		return getTeacher(accessor.getPublicId());
	}

	@Transactional
	public TeacherDto updateProfile(Accessor accessor, TeacherUpdateRequest request) {
		Teacher teacher = teacherRepository.findByPublicId(accessor.getPublicId())
				.orElseThrow(() -> new NotFoundException(TEACHER_NOT_FOUND));
		return TeacherDto.from(
				teacher.updateProfile(
						request.university(),
						request.major(),
						request.career(),
						request.bankName(),
						request.accountNumber(),
						request.accountHolder()
				)
		);
	}

	@Transactional(readOnly = true)
	public TeacherDto getTeacher(UUID publicId) {
		return TeacherDto.from(
				teacherRepository.findByPublicId(publicId)
						.orElseThrow(() -> new NotFoundException(TEACHER_NOT_FOUND))
		);
	}

	@Transactional
	public ProfileImageDto updateProfileImage(Accessor accessor, MultipartFile file) {
		Teacher teacher = teacherRepository.findByPublicId(accessor.getPublicId())
				.orElseThrow(() -> new NotFoundException(TEACHER_NOT_FOUND));

		String key = imageService.uploadProfileImage(
				TEACHER.toString(),
				accessor.getPublicId(),
				file
		);
		return ProfileImageDto.from(
				teacher.changeProfileImage(key)
		);
	}
}
