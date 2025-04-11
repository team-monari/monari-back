package com.monari.monariback.student.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.monari.monariback.auth.entity.Accessor;
import com.monari.monariback.common.exception.NotFoundException;
import com.monari.monariback.student.dto.StudentDto;
import com.monari.monariback.student.dto.request.StudentUpdateRequest;
import com.monari.monariback.student.entity.Student;
import com.monari.monariback.student.repository.StudentRepository;

import lombok.RequiredArgsConstructor;

import static com.monari.monariback.common.error.ErrorCode.STUDENT_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class StudentService {

	private final StudentRepository studentRepository;

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
}
