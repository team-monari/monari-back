package com.monari.monariback.enrollment.service;


import static com.monari.monariback.enrollment.constant.EnrollmentResponseConstants.ENROLLMENT_SUCCESS;

import com.monari.monariback.auth.entity.Accessor;
import com.monari.monariback.common.error.ErrorCode;
import com.monari.monariback.common.exception.BusinessException;
import com.monari.monariback.common.exception.NotFoundException;
import com.monari.monariback.enrollment.dto.request.EnrollmentCreateRequest;
import com.monari.monariback.enrollment.entity.Enrollment;
import com.monari.monariback.enrollment.repository.EnrollmentRepository;
import com.monari.monariback.lesson.entity.Lesson;
import com.monari.monariback.lesson.repository.LessonRepository;
import com.monari.monariback.student.entity.Student;
import com.monari.monariback.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final LessonRepository lessonRepository;

    public String enroll(
        final EnrollmentCreateRequest enrollmentCreateRequest,
        final Accessor accessor
    ) {

        final Student student = studentRepository.findByPublicId(accessor.getPublicId())
            .orElseThrow(() -> new NotFoundException(
                ErrorCode.STUDENT_NOT_FOUND)
            );

        final Lesson lesson = lessonRepository.findById(enrollmentCreateRequest.lessonId())
            .orElseThrow(() -> new NotFoundException(
                ErrorCode.LESSON_NOT_FOUND)
            );

        validateDuplicatedEnrollment(lesson, student);

        final Enrollment enrollment = Enrollment.ofCreate(student, lesson);

        enrollmentRepository.save(enrollment);

        return ENROLLMENT_SUCCESS;
    }

    private void validateDuplicatedEnrollment(final Lesson lesson, final Student student) {
        if (enrollmentRepository.existsByStudentIdAndLessonId(
            student.getId(),
            lesson.getId()
        )
        ) {
            throw new BusinessException(ErrorCode.ENROLLMENT_DUPLICATED);
        }
    }
}
