package com.monari.monariback.batch.dto;

import com.monari.monariback.enrollment.entity.Enrollment;
import com.monari.monariback.lesson.entity.Lesson;
import com.monari.monariback.teacher.entity.Teacher;

public record LessonCancelNoticeContextDto(
        String lessonTitle,
        String teacherName
) {

    public static LessonCancelNoticeContextDto from(Enrollment enrollment) {
        Lesson lesson = enrollment.getLesson();
        Teacher teacher = lesson.getTeacher();
        return new LessonCancelNoticeContextDto(
                lesson.getTitle(),
                teacher.getName()
        );
    }
}
