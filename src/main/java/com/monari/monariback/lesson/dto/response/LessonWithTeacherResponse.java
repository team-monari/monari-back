package com.monari.monariback.lesson.dto.response;

import java.time.LocalDate;

public record LessonWithTeacherResponse(
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
    String name,
    String university,
    String major,
    String career
) {

    public static LessonWithTeacherResponse ofCreate(
        final Integer lessonId,
        final Integer locationId,
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
        final String region,
        final String schoolLevel,
        final String subject,
        final String name,
        final String university,
        final String major,
        final String career
    ) {
        return new LessonWithTeacherResponse(
            lessonId,
            locationId,
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
            region,
            schoolLevel,
            subject,
            name,
            university,
            major,
            career
        );
    }

}
