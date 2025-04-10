package com.monari.monariback.study.dto.request;

import com.monari.monariback.common.enumerated.SchoolLevel;
import com.monari.monariback.common.enumerated.Subject;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record StudyUpdateRequest(
        @Size(max = 100) @NotNull String title,
        @Size(max = 5000) @NotNull String description,
        @NotNull Subject subject,
        @NotNull SchoolLevel schoolLevel,
        @Positive @NotNull Integer locationId
) {
}
