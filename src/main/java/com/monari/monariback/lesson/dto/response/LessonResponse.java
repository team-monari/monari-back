package com.monari.monariback.lesson.dto.response;

import com.monari.monariback.lesson.entity.Lesson;
import java.time.LocalDate;

public record LessonResponse(
    Integer lessonId,
    Integer locationId,
    Integer teacherId,
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
    String schoolLevel,
    String subject
) {

    public static LessonResponse ofCreate(
        final Integer lessonId,
        final Integer locationId,
        final Integer teacherId,
        final String title,
        final Integer currentStudent,
        final String description,
        final Integer amount,
        final Integer minStudent,
        final Integer maxStudent,
        final LocalDate startDate,
        final LocalDate endDate,
        final LocalDate deadline,
        final String status,
        final String schoolLevel,
        final String subject
    ) {
        return new LessonResponse(
            lessonId,
            locationId,
            teacherId,
            title,
            currentStudent,
            description,
            amount,
            minStudent,
            maxStudent,
            startDate,
            endDate,
            deadline,
            status,
            schoolLevel,
            subject
        );
    }

    public static LessonResponse ofCreatePage(Lesson lesson, Integer currentStudent) {
        return new LessonResponse(
            lesson.getId(),
            lesson.getLocation().getId(),
            lesson.getTeacher().getId(),
            lesson.getTitle(),
            currentStudent,
            lesson.getDescription(),
            lesson.getAmount(),
            lesson.getMinStudent(),
            lesson.getMaxStudent(),
            lesson.getStartDate(),
            lesson.getEndDate(),
            lesson.getDeadline(),
            lesson.getStatus().name(),
            lesson.getSchoolLevel().name(),
            lesson.getSubject().name()
        );
    }
}
