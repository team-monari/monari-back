package com.monari.monariback.student.dto;

import java.util.UUID;

import com.monari.monariback.common.enumerated.Grade;
import com.monari.monariback.common.enumerated.SchoolLevel;
import com.monari.monariback.student.entity.Student;

public record StudentDto(
		UUID publicId,
		String email,
		String name,
		SchoolLevel schoolLevel,
		Grade grade,
		String profileImageUrl
) {
	public static StudentDto from(Student student) {
		return new StudentDto(
				student.getPublicId(),
				student.getEmail(),
				student.getName(),
				student.getSchoolLevel(),
				student.getGrade(),
				student.getProfileImageUrl()
		);
	}
}
