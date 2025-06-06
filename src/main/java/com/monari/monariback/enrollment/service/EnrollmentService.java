package com.monari.monariback.enrollment.service;

import static com.monari.monariback.enrollment.constant.EnrollmentResponseConstants.ENROLLMENT_CANCELED;
import static com.monari.monariback.enrollment.constant.EnrollmentResponseConstants.ENROLLMENT_REFUND;
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

    /**
     * 학생이 수업을 신청하는 메서드
     *
     * @param enrollmentCreateRequest 수업 신청 요청 정보 (수업 ID 포함)
     * @param accessor                로그인된 사용자 정보
     * @return 신청 성공 메시지
     * @throws NotFoundException 학생 또는 수업이 존재하지 않을 경우 발생
     * @throws BusinessException 이미 신청한 수업일 경우 발생
     * @author Hong
     */
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

    /**
     * 학생이 중복으로 수업을 신청했는지 검증하는 메서드
     *
     * @param lesson  수업 엔티티
     * @param student 학생 엔티티
     * @throws BusinessException 이미 수업을 신청한 경우 예외 발생
     * @author Hong
     */
    private void validateDuplicatedEnrollment(final Lesson lesson, final Student student) {
        if (enrollmentRepository.existsByStudentIdAndLessonId(
            student.getId(),
            lesson.getId()
        )
        ) {
            throw new BusinessException(ErrorCode.ENROLLMENT_DUPLICATED);
        }
    }

    /**
     * 특정 수업에 등록된 학생 목록을 조회하는 메서드
     *
     * @param lessonId 수업 ID
     * @return 수업에 등록된 학생들의 목록 (EnrollmentResponse 형태)
     * @author Hong
     */
    @Transactional(readOnly = true)
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

    /**
     * 결제 상태를 수정하는 메서드
     *
     * @param studentId 학생 public ID
     * @param lessonId  수업 ID
     * @param status    변경할 결제 상태 (예: REQUESTED, COMPLETED 등)
     * @return 수정된 학생의 Enrollment 정보
     * @throws BusinessException 학생이 없거나 수강 정보가 없을 경우 발생
     * @author Hong
     */
    public EnrollmentResponse modifyStatus(
        final UUID studentId,
        final Integer lessonId,
        final EnrollmentStatus status
    ) {
        final Student student = studentRepository.findByPublicId(studentId)
            .orElseThrow(() -> new BusinessException(ErrorCode.STUDENT_NOT_FOUND));

        final Enrollment enrollment = enrollmentRepository.findByLessonIdAndStudentId(
            student.getId(), lessonId).orElseThrow(() -> new NotFoundException(
                ErrorCode.ENROLLMENT_NOT_FOUND
            )
        );

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

    public String cancelEnrollmentByStudent(
        final Integer lessonId,
        final Accessor accessor
    ) {
        final Enrollment enrollment = enrollmentRepository.findByStudentAndLesson(
                accessor.getPublicId(),
                lessonId
            )
            .orElseThrow(() -> new NotFoundException(ErrorCode.ENROLLMENT_NOT_FOUND));

        enrollment.cancelByStudent();
        return ENROLLMENT_CANCELED;
    }

    public String refundEnrollmentByStudent(
        final Integer lessonId,
        final Accessor accessor
    ) {
        final Enrollment enrollment = enrollmentRepository.findByStudentAndLesson(
                accessor.getPublicId(),
                lessonId
            )
            .orElseThrow(() -> new BusinessException(ErrorCode.ENROLLMENT_NOT_FOUND));
        enrollment.refundByStudent();
        return ENROLLMENT_REFUND;
    }
}
