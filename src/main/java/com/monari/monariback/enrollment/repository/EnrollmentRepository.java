package com.monari.monariback.enrollment.repository;

import com.monari.monariback.enrollment.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer> {

    Integer countByLessonId(Integer lessonId);

    boolean existsByStudentIdAndLessonId(Integer studentId, Integer lessonId);
}
