package com.monari.monariback.study.dto.request;

import com.monari.monariback.common.enumerated.SchoolLevel;
import com.monari.monariback.common.enumerated.Subject;

public record StudyCreateRequest (
        String title,
        String description,
        Subject subject,
        SchoolLevel schoolLevel,
        Integer locationId
) {
}
