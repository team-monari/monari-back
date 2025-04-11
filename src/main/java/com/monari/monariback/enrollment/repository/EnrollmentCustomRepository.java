package com.monari.monariback.enrollment.repository;

import com.monari.monariback.enrollment.entity.Enrollment;
import java.util.List;

public interface EnrollmentCustomRepository {

    Integer countByLessonId(final Integer lessonId);

    boolean existsByStudentIdAndLessonId(final Integer studentId, final Integer lessonId);

    List<Enrollment> findAllByStudentId(final Integer studentId);
}
