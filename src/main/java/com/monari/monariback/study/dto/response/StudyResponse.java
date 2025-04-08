package com.monari.monariback.study.dto.response;

import com.monari.monariback.study.entity.Study;

public record StudyResponse(
        Integer id,
        String title,
        String description,
        String subject,
        String schoolLevel,
        String status,
        String locationName,
        String locationServiceUrl
) {

    public static StudyResponse from(Study study) {
        return new StudyResponse(
                study.getId(),
                study.getTitle(),
                study.getDescription(),
                study.getSubject().name(),
                study.getSchoolLevel().name(),
                study.getStatus().name(),
                study.getLocation().getLocationName(),
                study.getLocation().getServiceUrl()
        );
    }
}
