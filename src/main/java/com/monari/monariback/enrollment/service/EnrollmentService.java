package com.monari.monariback.enrollment.service;


import static com.monari.monariback.enrollment.constant.EnrollmentResponseConstants.ENROLLMENT_SUCCESS;

import com.monari.monariback.auth.entity.Accessor;
import com.monari.monariback.common.error.ErrorCode;
import com.monari.monariback.common.exception.BusinessException;
import com.monari.monariback.common.exception.NotFoundException;
import com.monari.monariback.enrollment.dto.request.EnrollmentCreateRequest;
import com.monari.monariback.enrollment.dto.response.EnrollmentResponse;
import com.monari.monariback.enrollment.entity.Enrollment;
import com.monari.monariback.enrollment.entity.enumerated.EnrollmentStatus;
import com.monari.monariback.enrollment.repository.EnrollmentRepository;
import com.monari.monariback.lesson.entity.Lesson;
import com.monari.monariback.lesson.repository.LessonRepository;
import com.monari.monariback.student.entity.Student;
import com.monari.monariback.student.repository.StudentRepository;
import java.util.List;
import java.util.UUID;
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

    public List<EnrollmentResponse> getStudentList(
        final Integer lessonId
    ) {
        return enrollmentRepository
            .findAllByLessonId(lessonId).stream()
            .map(enrollment -> EnrollmentResponse.ofCreate(
                enrollment.getStudent().getPublicId(),
                enrollment.getStudent().getName(),
                enrollment.getStudent().getSchoolName(),
                enrollment.getStudent().getSchoolLevel(),
                enrollment.getStudent().getGrade(),
                enrollment.getStatus(),
                enrollment.getFinalPrice(),
                enrollment.getCreatedAt()
            ))
            .toList();
    }

    public EnrollmentResponse modifyStatus(
        final UUID studentId,
        final Integer lessonId,
        final EnrollmentStatus status
    ) {
        final Student student = studentRepository.findByPublicId(studentId)
            .orElseThrow(() -> new BusinessException(ErrorCode.STUDENT_NOT_FOUND));

        final Enrollment enrollment = enrollmentRepository.findByLessonIdAndStudentId(
            student.getId(), lessonId);

        enrollment.updateStatus(status);

        return EnrollmentResponse.ofCreate(
            studentId, student.getName(),
            student.getSchoolName(),
            student.getSchoolLevel(),
            student.getGrade(),
            enrollment.getStatus(),
            enrollment.getFinalPrice(),
            enrollment.getCreatedAt()
        );
    }
}
