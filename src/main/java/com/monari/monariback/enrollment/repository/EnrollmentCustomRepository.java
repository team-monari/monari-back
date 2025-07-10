package com.monari.monariback.enrollment.repository;

import com.monari.monariback.enrollment.entity.Enrollment;
import com.monari.monariback.enrollment.entity.enumerated.EnrollmentStatus;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EnrollmentCustomRepository {

    Integer countCurrentStudentByLessonId(final Integer lessonId);

    boolean existsByStudentIdAndLessonId(final Integer studentId, final Integer lessonId);

    List<Enrollment> findAllByLessonId(
        final Integer LessonId
    );

    Optional<Enrollment> findByStudentAndLesson(final UUID studentId, final Integer lessonId);

    Long countByStudentId(final Integer studentId);

    Optional<Enrollment> findByLessonIdAndStudentId(
        final Integer studentId,
        final Integer lessonId
    );

    void updateFinalPrice(final List<Integer> enrollmentIds, final int finalPrice, final EnrollmentStatus status);
}
