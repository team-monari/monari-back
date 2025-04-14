package com.monari.monariback.teacher.dto.request;

import static com.monari.monariback.teacher.constant.TeacherValidationConstants.*;

import jakarta.validation.constraints.Size;

public record TeacherUpdateRequest(
		@Size(max = MAX_UNIVERSITY_LENGTH, message = UNIVERSITY_LENGTH_MESSAGE)
		String university,

		@Size(max = MAX_MAJOR_LENGTH, message = MAJOR_LENGTH_MESSAGE)
		String major,

		@Size(max = MAX_CAREER_LENGTH, message = CAREER_LENGTH_MESSAGE)
		String career,

		@Size(max = MAX_PROFILE_IMAGE_URL_LENGTH, message = PROFILE_IMAGE_URL_LENGTH_MESSAGE)
		String profileImageUrl,

		@Size(max = MAX_BANK_NAME_LENGTH, message = BANK_NAME_LENGTH_MESSAGE)
		String bankName,

		@Size(max = MAX_ACCOUNT_NUMBER_LENGTH, message = ACCOUNT_NUMBER_LENGTH_MESSAGE)
		String accountNumber,

		@Size(max = MAX_ACCOUNT_HOLDER_LENGTH, message = ACCOUNT_HOLDER_LENGTH_MESSAGE)
		String accountHolder
) {
}
