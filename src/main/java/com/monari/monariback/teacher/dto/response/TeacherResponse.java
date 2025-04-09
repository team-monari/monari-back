package com.monari.monariback.teacher.dto.response;

import java.util.UUID;

import com.monari.monariback.teacher.dto.TeacherDto;

public record TeacherResponse(
		UUID publicID,
		String email,
		String name,
		String university,
		String major,
		String career,
		String profileImageUrl
) {
	public static TeacherResponse from(TeacherDto teacherDto) {
		return new TeacherResponse(
				teacherDto.publicID(),
				teacherDto.email(),
				teacherDto.name(),
				teacherDto.university(),
				teacherDto.major(),
				teacherDto.career(),
				teacherDto.profileImageUrl()
		);
	}
}
