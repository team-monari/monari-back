package com.monari.monariback.enrollment.dto.request;

public record EnrollmentCreateRequest(
    // TODO : Jwt 적용
    Long studentId,
    Integer lessonId
) {

}
