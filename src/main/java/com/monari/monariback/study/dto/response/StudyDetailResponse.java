package com.monari.monariback.study.dto.response;

import com.monari.monariback.common.enumerated.Region;
import com.monari.monariback.common.enumerated.SchoolLevel;
import com.monari.monariback.common.enumerated.Subject;
import com.monari.monariback.study.entity.Study;
import com.monari.monariback.study.enumerated.StudyStatus;
import com.monari.monariback.study.enumerated.StudyType;

import java.time.LocalDateTime;
import java.util.UUID;

public record StudyDetailResponse(
        Integer id,
        String title,
        String description,
        Subject subject,
        SchoolLevel schoolLevel,
        Region region,
        StudyStatus status,
        StudyType studyType,
        LocalDateTime createdAt,
        Integer locationId,
        Integer generalLocationId,
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
                study.getRegion(),
                study.getStatus(),
                study.getStudyType(),
                study.getCreatedAt(),
                study.getLocation() != null ? study.getLocation().getId() : null,
                study.getGeneralLocation() != null ? study.getGeneralLocation().getId() : null,
                study.getStudent().getPublicId(),
                study.getStudent().getName()
        );
    }
}
