package com.monari.monariback.enrollment.repository;

import com.monari.monariback.enrollment.entity.Enrollment;
import java.util.List;

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

    Long countByStudentId(final Integer studentId);

    Enrollment findByLessonIdAndStudentId(
        final Integer studentId,
        final Integer lessonId
    );
}
