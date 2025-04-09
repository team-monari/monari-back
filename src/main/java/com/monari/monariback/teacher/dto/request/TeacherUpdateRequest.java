package com.monari.monariback.teacher.dto.request;

public record TeacherUpdateRequest(
		String university,
		String major,
		String career,
		String profileImageUrl
) {
}
