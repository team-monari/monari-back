package com.monari.monariback.enrollment.repository;

import com.monari.monariback.enrollment.entity.Enrollment;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EnrollmentCustomRepository {

    Integer countCurrentStudentByLessonId(final Integer lessonId);

    boolean existsByStudentIdAndLessonId(final Integer studentId, final Integer lessonId);

    List<Enrollment> findAllByLessonId(
        final Integer LessonId
    );

    List<Enrollment> findAllByStudentIdWithPagination(
        final Integer studentId,
        final Integer pageNumber,
        final Integer pageSize
    );

    Optional<Enrollment> findByStudentAndLesson(final UUID studentId, final Integer lessonId);

    Long countByStudentId(final Integer studentId);

    Optional<Enrollment> findByLessonIdAndStudentId(
        final Integer studentId,
        final Integer lessonId
    );
}
