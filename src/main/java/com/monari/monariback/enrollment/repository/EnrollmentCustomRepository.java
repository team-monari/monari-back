package com.monari.monariback.enrollment.repository;

public interface EnrollmentCustomRepository {

    Integer countByLessonId(final Integer lessonId);

    boolean existsByStudentIdAndLessonId(final Integer studentId, final Integer lessonId);

}
