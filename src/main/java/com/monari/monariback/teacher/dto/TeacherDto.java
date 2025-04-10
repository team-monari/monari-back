package com.monari.monariback.teacher.dto;

import java.util.UUID;

import com.monari.monariback.teacher.entity.Teacher;

public record TeacherDto(
		UUID publicID,
		String email,
		String name,
		String university,
		String major,
		String career,
		String profileImageUrl
) {
	public static TeacherDto from(Teacher teacher) {
		return new TeacherDto(
				teacher.getPublicId(),
				teacher.getEmail(),
				teacher.getName(),
				teacher.getUniversity(),
				teacher.getMajor(),
				teacher.getCareer(),
				teacher.getProfileImageUrl()
		);
	}
}
