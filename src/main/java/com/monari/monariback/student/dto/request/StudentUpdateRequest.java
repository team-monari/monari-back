package com.monari.monariback.student.dto.request;

import static com.monari.monariback.teacher.constant.TeacherValidationConstants.*;

import com.monari.monariback.common.enumerated.Grade;
import com.monari.monariback.common.enumerated.SchoolLevel;

import jakarta.validation.constraints.Size;

public record StudentUpdateRequest(

		SchoolLevel schoolLevel,
		Grade grade,

		@Size(max = MAX_PROFILE_IMAGE_URL_LENGTH, message = PROFILE_IMAGE_URL_LENGTH_MESSAGE)
		String profileImageUrl
) {
}
