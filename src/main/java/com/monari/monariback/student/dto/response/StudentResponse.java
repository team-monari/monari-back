package com.monari.monariback.student.dto.response;

import java.util.UUID;

import com.monari.monariback.common.enumerated.Grade;
import com.monari.monariback.common.enumerated.SchoolLevel;
import com.monari.monariback.student.dto.StudentDto;

public record StudentResponse(
		UUID publicId,
		String email,
		String name,
		SchoolLevel schoolLevel,
		Grade grade,
		String profileImageUrl
) {
	public static StudentResponse from(StudentDto studentDto) {
		return new StudentResponse(
				studentDto.publicId(),
				studentDto.email(),
				studentDto.name(),
				studentDto.schoolLevel(),
				studentDto.grade(),
				studentDto.profileImageUrl()
		);
	}
}
