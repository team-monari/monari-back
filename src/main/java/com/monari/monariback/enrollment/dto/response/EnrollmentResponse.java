package com.monari.monariback.enrollment.dto.response;

import com.monari.monariback.common.enumerated.Grade;
import com.monari.monariback.common.enumerated.SchoolLevel;
import com.monari.monariback.enrollment.entity.enumerated.EnrollmentStatus;
import java.time.LocalDateTime;
import java.util.UUID;

public record EnrollmentResponse(
    UUID publicId,
    String name,
    String schoolName,
    SchoolLevel schoolLevel,
    Grade grade,
    EnrollmentStatus status,
    Integer finalPrice,
    LocalDateTime createDateTime
) {

    public static EnrollmentResponse ofCreate(
        final UUID publicId,
        final String name,
        final String schoolName,
        final SchoolLevel schoolLevel,
        final Grade grade,
        final EnrollmentStatus status,
        final Integer finalPrice,
        final LocalDateTime createDateTime
    ) {

        return new EnrollmentResponse(
            publicId,
            name,
            schoolName,
            schoolLevel,
            grade,
            status,
            finalPrice,
            createDateTime);
    }

}
