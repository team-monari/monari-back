package com.monari.monariback.student.dto;

import java.util.UUID;

import com.monari.monariback.common.enumerated.Grade;
import com.monari.monariback.common.enumerated.SchoolLevel;
import com.monari.monariback.student.entity.Student;

public record StudentDto(
		UUID publicId,
		String email,
		String name,
		String schoolName,
		SchoolLevel schoolLevel,
		Grade grade
) {
	public static StudentDto from(Student student) {
		return new StudentDto(
				student.getPublicId(),
				student.getEmail(),
				student.getName(),
				student.getSchoolName(),
				student.getSchoolLevel(),
				student.getGrade()
		);
	}
}
