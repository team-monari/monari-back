package com.monari.monariback.student.dto.request;

import com.monari.monariback.common.enumerated.Grade;
import com.monari.monariback.common.enumerated.SchoolLevel;

public record StudentUpdateRequest(
		String schoolName,
		SchoolLevel schoolLevel,
		Grade grade
) {
}
