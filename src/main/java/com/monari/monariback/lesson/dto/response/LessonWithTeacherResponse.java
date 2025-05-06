package com.monari.monariback.lesson.dto.response;

import java.time.LocalDate;
import java.util.UUID;

public record LessonWithTeacherResponse(
    UUID publicTeacherId,
    Integer lessonId,
    Integer locationId,
    String title,
    Integer currentStudent,
    String description,
    Integer amount,
    Integer minStudent,
    Integer maxStudent,
    LocalDate startDate,
    LocalDate endDate,
    LocalDate deadline,
    String status,
    String region,
    String schoolLevel,
    String subject,
    String lessonType,
    String name,
    String university,
    String major,
    String career,
    String locationName,
    String x,
    String y,
    String serviceUrl
) {

}
