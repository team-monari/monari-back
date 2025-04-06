package com.monari.monariback.lesson.dto.response;

import java.time.LocalDate;

public record LessonResponse(
    Integer lessonId,
    Integer locationId,
    Integer teacherId,
    String description,
    Integer amount,
    Integer minStudent,
    Integer maxStudent,
    LocalDate startDate,
    LocalDate endDate,
    LocalDate deadline,
    String status,
    String schoolLevel,
    String subject
) {

}
