package com.monari.monariback.teacher.dto.response;

import java.util.UUID;

import com.monari.monariback.teacher.dto.TeacherDto;

public record TeacherResponse(
		UUID publicId,
		String email,
		String name,
		String university,
		String major,
		String career,
		String bankName,
		String accountNumber,
		String accountHolder
) {
	public static TeacherResponse from(TeacherDto teacherDto) {
		return new TeacherResponse(
				teacherDto.publicId(),
				teacherDto.email(),
				teacherDto.name(),
				teacherDto.university(),
				teacherDto.major(),
				teacherDto.career(),
				teacherDto.bankName(),
				teacherDto.accountNumber(),
				teacherDto.accountHolder()
		);
	}
}
