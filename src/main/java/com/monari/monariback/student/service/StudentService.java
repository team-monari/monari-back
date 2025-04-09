package com.monari.monariback.student.service;

import static com.monari.monariback.global.config.error.ErrorCode.*;

import org.springframework.stereotype.Service;

import com.monari.monariback.auth.entity.Accessor;
import com.monari.monariback.global.config.error.exception.NotFoundException;
import com.monari.monariback.student.dto.StudentDto;
import com.monari.monariback.student.repository.StudentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentService {

	private final StudentRepository studentRepository;

	public StudentDto findMyProfile(Accessor accessor) {
		return StudentDto.from(
				studentRepository.findByPublicId(accessor.getPublicId())
						.orElseThrow(() -> new NotFoundException(STUDENT_NOT_FOUND))
		);
	}
}
