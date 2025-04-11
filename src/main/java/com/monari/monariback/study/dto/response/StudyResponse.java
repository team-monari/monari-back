package com.monari.monariback.study.dto.response;

import com.monari.monariback.common.enumerated.SchoolLevel;
import com.monari.monariback.common.enumerated.Subject;
import com.monari.monariback.study.dto.StudyDto;
import com.monari.monariback.study.enumerated.StudyStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record StudyResponse(
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

    public static StudyResponse from(StudyDto studyDto) {
        return new StudyResponse(
                studyDto.id(),
                studyDto.title(),
                studyDto.description(),
                studyDto.subject(),
                studyDto.schoolLevel(),
                studyDto.status(),
                studyDto.createdAt(),
                studyDto.locationName(),
                studyDto.locationServiceUrl(),
                studyDto.studentPublicId(),
                studyDto.studentName()
        );
    }
}
