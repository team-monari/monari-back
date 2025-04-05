package com.monari.monariback.lesson.dto.request;

import com.monari.monariback.common.enumerated.SchoolLevel;
import com.monari.monariback.lesson.entity.enurmurated.LessonStatus;
import com.monari.monariback.common.enumerated.Subject;
import java.time.LocalDate;

public record CreateLessonRequest(
    Integer locationId,
    Integer teacherId,
    String description,
    Integer amount,
    Integer minStudent,
    Integer maxStudent,
    LocalDate startDate,
    LocalDate endDate,
    LocalDate deadline,
    LessonStatus status,
    SchoolLevel schoolLevel,
    Subject subject
) {

}
