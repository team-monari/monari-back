package com.monari.monariback.study.dto.request;

import com.monari.monariback.common.enumerated.Region;
import com.monari.monariback.common.enumerated.SchoolLevel;
import com.monari.monariback.common.enumerated.Subject;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import static com.monari.monariback.study.constant.StudyValidationConstants.*;
import static com.monari.monariback.study.constant.StudyValidationConstants.MAX_DESCRIPTION_LENGTH_MESSAGE;

public record StudyEditRequest(
        @NotNull
        @Size(max = MAX_TITLE_LENGTH, message = MAX_TITLE_LENGTH_MESSAGE)
        String title,

        @NotNull
        @Size(max = MAX_DESCRIPTION_LENGTH, message = MAX_DESCRIPTION_LENGTH_MESSAGE)
        String description,

        @NotNull Subject subject,
        @NotNull SchoolLevel schoolLevel,
        @Nullable Region region,
        @Positive @Nullable Integer locationId,
        @Positive @Nullable Integer generalLocationId
) {
}
