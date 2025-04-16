package com.monari.monariback.study.dto;

import com.monari.monariback.common.enumerated.Region;
import com.monari.monariback.common.enumerated.SchoolLevel;
import com.monari.monariback.common.enumerated.Subject;
import com.monari.monariback.study.enumerated.StudyStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record StudyDto(
        Integer id,
        String title,
        String description,
        Subject subject,
        SchoolLevel schoolLevel,
        Region region,
        StudyStatus status,
        LocalDateTime createdAt,
        String locationName,
        String locationServiceUrl,
        UUID studentPublicId,
        String studentName
) {
}
