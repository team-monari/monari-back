package com.monari.monariback.student.dto;

import com.monari.monariback.common.enumerated.Grade;
import com.monari.monariback.common.enumerated.SchoolLevel;
import com.monari.monariback.student.entity.Student;

public record StudentDto(
		String email,
		String name,
		SchoolLevel schoolLevel,
		Grade grade
) {
	public static StudentDto from(Student student) {
		return new StudentDto(
				student.getEmail(),
				student.getName(),
				student.getSchoolLevel(),
				student.getGrade()
		);
	}
}
