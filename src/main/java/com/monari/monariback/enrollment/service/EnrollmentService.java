package com.monari.monariback.enrollment.service;


import com.monari.monariback.enrollment.dto.request.EnrollmentCreateRequest;
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

    public String enroll(
        final EnrollmentCreateRequest enrollmentCreateRequest) {

        validateDuplicatedEnrollment(enrollmentCreateRequest);

        Student student = studentRepository.findById(enrollmentCreateRequest.studentId())
            .orElseThrow(() -> new NotFoundException(
                ErrorCode.STUDENT_NOT_FOUND)
            );

        Lesson lesson = lessonRepository.findById(enrollmentCreateRequest.lessonId())
            .orElseThrow(() -> new NotFoundException(
                ErrorCode.LESSON_NOT_FOUND)
            );

        Enrollment enrollment = Enrollment.ofCreate(student, lesson);

        enrollmentRepository.save(enrollment);

        return "등록에 성공하였습니다";
    }
    
    private void validateDuplicatedEnrollment(EnrollmentCreateRequest enrollmentCreateRequest) {
        if (enrollmentRepository.existsByStudentIdAndLessonId(
            enrollmentCreateRequest.studentId(),
            enrollmentCreateRequest.lessonId())
        ) {
            throw new BusinessException(ErrorCode.ENROLLMENT_DUPLICATED);
        }
    }
}
