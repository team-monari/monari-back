package com.monari.monariback.batch.dto;

import com.monari.monariback.enrollment.entity.Enrollment;
import com.monari.monariback.lesson.entity.Lesson;
import com.monari.monariback.teacher.entity.Teacher;

public record LessonFeeNoticeContextDto(
        String lessonTitle,
        int lessonFinalPrice,
        String teacherName,
        String bankName,
        String accountNumber,
        String accountHolder
) {

    public static LessonFeeNoticeContextDto from(Enrollment enrollment) {
        Lesson lesson = enrollment.getLesson();
        Teacher teacher = lesson.getTeacher();
        return new LessonFeeNoticeContextDto(
                lesson.getTitle(),
                enrollment.getFinalPrice(),
                teacher.getName(),
                teacher.getBankName(),
                teacher.getAccountNumber(),
                teacher.getAccountHolder()
        );
    }
}
