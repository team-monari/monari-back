package com.monari.monariback.lesson.dto.request;

import com.monari.monariback.lesson.entity.enurmurated.LessonStatus;
import com.monari.monariback.lesson.entity.enurmurated.SchoolLevel;
import com.monari.monariback.lesson.entity.enurmurated.Subject;
import java.time.LocalDate;

public record UpdateLessonRequest(
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
