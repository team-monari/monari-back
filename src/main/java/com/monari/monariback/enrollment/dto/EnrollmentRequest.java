package com.monari.monariback.enrollment.dto;

public record EnrollmentRequest(
    // TODO : Jwt 적용
    Long studentId,
    Integer lessonId
) {

}
