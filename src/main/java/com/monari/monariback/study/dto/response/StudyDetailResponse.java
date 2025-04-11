package com.monari.monariback.study.dto.response;

import com.monari.monariback.common.enumerated.SchoolLevel;
import com.monari.monariback.common.enumerated.Subject;
import com.monari.monariback.study.entity.Study;
import com.monari.monariback.study.enumerated.StudyStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record StudyDetailResponse(
        Integer id,
        String title,
        String description,
        Subject subject,
        SchoolLevel schoolLevel,
        StudyStatus status,
        LocalDateTime createdAt,
        String locationName,
        String locationServiceUrl,
        UUID studentPublicId,
        String studentName
) {

    public static StudyDetailResponse from(Study study) {
        return new StudyDetailResponse(
                study.getId(),
                study.getTitle(),
                study.getDescription(),
                study.getSubject(),
                study.getSchoolLevel(),
                study.getStatus(),
                study.getCreatedAt(),
                study.getLocation().getLocationName(),
                study.getLocation().getServiceUrl(),
                study.getStudent().getPublicId(),
                study.getStudent().getName()
        );
    }
}
