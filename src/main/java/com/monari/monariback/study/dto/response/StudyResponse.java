package com.monari.monariback.study.dto.response;

import com.monari.monariback.common.enumerated.Region;
import com.monari.monariback.common.enumerated.SchoolLevel;
import com.monari.monariback.common.enumerated.Subject;
import com.monari.monariback.study.dto.StudyDto;
import com.monari.monariback.study.enumerated.StudyStatus;
import com.monari.monariback.study.enumerated.StudyType;

import java.time.LocalDateTime;
import java.util.UUID;

public record StudyResponse(
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

    public static StudyResponse from(StudyDto studyDto) {
        return new StudyResponse(
                studyDto.id(),
                studyDto.title(),
                studyDto.description(),
                studyDto.subject(),
                studyDto.schoolLevel(),
                studyDto.region(),
                studyDto.status(),
                studyDto.studyType(),
                studyDto.createdAt(),
                studyDto.locationId(),
                studyDto.generalLocationId(),
                studyDto.studentPublicId(),
                studyDto.studentName()
        );
    }
}
