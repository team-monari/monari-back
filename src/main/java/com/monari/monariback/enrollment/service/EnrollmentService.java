package com.monari.monariback.enrollment.service;


import com.monari.monariback.enrollment.dto.EnrollmentRequest;
import com.monari.monariback.enrollment.entity.Enrollment;
import com.monari.monariback.enrollment.repository.EnrollmentRepository;
import com.monari.monariback.global.config.error.ErrorCode;
import com.monari.monariback.global.config.error.exception.BusinessException;
import com.monari.monariback.global.config.error.exception.NotFoundException;
import com.monari.monariback.lesson.entity.Lesson;
import com.monari.monariback.lesson.repository.LessonRepository;
import com.monari.monariback.student.domain.Student;
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

    public Integer enrollment(
        final EnrollmentRequest enrollmentRequest) {

        if (enrollmentRepository.existsByStudentIdAndLessonId(
            enrollmentRequest.studentId(),
            enrollmentRequest.lessonId())
        ) {
            throw new BusinessException(ErrorCode.ENROLLMENT_DUPLICATED);
        }

        Student student = studentRepository.findById(enrollmentRequest.studentId())
            .orElseThrow(() -> new NotFoundException(
                ErrorCode.STUDENT_NOT_FOUND)
            );

        Lesson lesson = lessonRepository.findById(enrollmentRequest.lessonId())
            .orElseThrow(() -> new NotFoundException(
                ErrorCode.LESSON_NOT_FOUND)
            );

        Enrollment enrollment = Enrollment.ofCreate(student, lesson);

        enrollmentRepository.save(enrollment);

        return enrollment.getId();
    }

}
