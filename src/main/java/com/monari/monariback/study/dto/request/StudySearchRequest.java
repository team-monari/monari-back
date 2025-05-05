package com.monari.monariback.study.dto.request;

import com.monari.monariback.common.enumerated.Region;
import com.monari.monariback.common.enumerated.SchoolLevel;
import com.monari.monariback.common.enumerated.Subject;
import com.monari.monariback.study.enumerated.StudyType;
import jakarta.annotation.Nullable;

public record StudySearchRequest(
        @Nullable String titleKeyword,
        @Nullable String descriptionKeyword,
        @Nullable SchoolLevel schoolLevel,
        @Nullable Subject subject,
        @Nullable Region region,
        @Nullable StudyType studyType
) {
}
