package com.monari.monariback.teacher.service;

import static com.monari.monariback.global.config.error.ErrorCode.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.monari.monariback.auth.entity.Accessor;
import com.monari.monariback.global.config.error.exception.NotFoundException;
import com.monari.monariback.teacher.dto.TeacherDto;
import com.monari.monariback.teacher.repository.TeacherRepository;

import lombok.RequiredArgsConstructor;

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
}
