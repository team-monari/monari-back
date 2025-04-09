package com.monari.monariback.study.dto.request;

import com.monari.monariback.common.enumerated.SchoolLevel;
import com.monari.monariback.common.enumerated.Subject;

public record StudyUpdateRequest(
        String title,
        String description,
        Subject subject,
        SchoolLevel schoolLevel,
        Integer locationId
) {
}
