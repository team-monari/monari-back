package com.monari.monariback.teacher.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.monari.monariback.auth.entity.Accessor;
import com.monari.monariback.common.exception.NotFoundException;
import com.monari.monariback.teacher.dto.TeacherDto;
import com.monari.monariback.teacher.dto.request.TeacherUpdateRequest;
import com.monari.monariback.teacher.entity.Teacher;
import com.monari.monariback.teacher.repository.TeacherRepository;

import lombok.RequiredArgsConstructor;

import static com.monari.monariback.common.error.ErrorCode.TEACHER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class TeacherService {

	private final TeacherRepository teacherRepository;

	@Transactional(readOnly = true)
	public TeacherDto findMyProfile(Accessor accessor) {
		return TeacherDto.from(
				teacherRepository.findByPublicId(accessor.getPublicId())
						.orElseThrow(() -> new NotFoundException(TEACHER_NOT_FOUND))
		);
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
						request.profileImageUrl(),
						request.bankName(),
						request.accountNumber(),
						request.accountHolder()
				)
		);
	}
}
