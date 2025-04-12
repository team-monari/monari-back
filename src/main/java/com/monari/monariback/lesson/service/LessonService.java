package com.monari.monariback.lesson.service;

import static com.monari.monariback.common.error.ErrorCode.STUDENT_NOT_FOUND;
import static com.monari.monariback.common.error.ErrorCode.TEACHER_NOT_FOUND;
import static com.monari.monariback.lesson.constant.LessonResponseConstants.LESSON_CREATE_SUCCESS;
import static com.monari.monariback.lesson.constant.LessonResponseConstants.LESSON_UPDATE_SUCCESS;

import com.monari.monariback.auth.entity.Accessor;
import com.monari.monariback.common.error.ErrorCode;
import com.monari.monariback.common.exception.BusinessException;
import com.monari.monariback.common.exception.NotFoundException;
import com.monari.monariback.enrollment.repository.EnrollmentRepository;
import com.monari.monariback.lesson.dto.request.CreateLessonRequest;
import com.monari.monariback.lesson.dto.request.SearchLessonRequest;
import com.monari.monariback.lesson.dto.request.UpdateLessonRequest;
import com.monari.monariback.lesson.dto.response.LessonResponse;
import com.monari.monariback.lesson.dto.response.PageInfoResponse;
import com.monari.monariback.lesson.entity.Lesson;
import com.monari.monariback.lesson.repository.LessonRepository;
import com.monari.monariback.location.entity.Location;
import com.monari.monariback.location.repository.LocationRepository;
import com.monari.monariback.student.entity.Student;
import com.monari.monariback.student.repository.StudentRepository;
import com.monari.monariback.teacher.entity.Teacher;
import com.monari.monariback.teacher.repository.TeacherRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class LessonService {

    private static final Integer PAGE_SIZE = 6;

    private final LessonRepository lessonRepository;
    private final LocationRepository locationRepository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;
    private final EnrollmentRepository enrollmentRepository;

    /**
     * 수업 생성
     *
     * @param lessonDto 수업 생성 요청 데이터
     * @param accessor  현재 로그인한 사용자 정보 (선생님)
     * @return 수업 생성 성공 메시지
     * @author Hong
     */

    public String createLesson(
        final CreateLessonRequest lessonDto,
        final Accessor accessor
    ) {
        final Location location = locationRepository.findById(lessonDto.locationId())
            .orElseThrow(() -> new NotFoundException(ErrorCode.LOCATION_NOT_FOUND));
        final Teacher teacher = teacherRepository.findByPublicId(accessor.getPublicId())
            .orElseThrow(() -> new NotFoundException(TEACHER_NOT_FOUND));

        final Lesson lesson = Lesson.ofCreate(
            location,
            teacher,
            lessonDto.title(),
            lessonDto.description(),
            lessonDto.amount(),
            lessonDto.minStudent(),
            lessonDto.maxStudent(),
            lessonDto.startDate(),
            lessonDto.endDate(),
            lessonDto.deadline(),
            lessonDto.schoolLevel(),
            lessonDto.subject()
        );

        lessonRepository.save(lesson);
        return LESSON_CREATE_SUCCESS;
    }

    /**
     * 수업 수정
     *
     * @param lessonId  수정할 수업 ID
     * @param lessonDto 수정 요청 데이터
     * @param accessor  현재 로그인한 사용자 정보 (선생님)
     * @return 수업 수정 성공 메시지
     * @author Hong
     */
    public String updateLesson(
        final Integer lessonId,
        final UpdateLessonRequest lessonDto,
        final Accessor accessor
    ) {
        Lesson lesson = lessonRepository.findById(lessonId)
            .orElseThrow(() -> new NotFoundException(ErrorCode.LESSON_NOT_FOUND));

        final Location location = locationRepository.findById(lessonDto.locationId())
            .orElseThrow(() -> new NotFoundException(ErrorCode.LOCATION_NOT_FOUND));
        final Teacher teacher = teacherRepository.findByPublicId(accessor.getPublicId())
            .orElseThrow(() -> new NotFoundException(TEACHER_NOT_FOUND));

        lesson.update(
            location,
            teacher,
            lessonDto.title(),
            lessonDto.description(),
            lessonDto.amount(),
            lessonDto.minStudent(),
            lessonDto.maxStudent(),
            lessonDto.startDate(),
            lessonDto.endDate(),
            lessonDto.deadline(),
            lessonDto.schoolLevel(),
            lessonDto.subject()
        );

        return LESSON_UPDATE_SUCCESS;
    }

    /**
     * 수업 상세 조회
     *
     * @param lessonId 조회할 수업 ID
     * @return 수업 상세 응답 DTO
     * @author Hong
     */
    @Transactional(readOnly = true)
    public LessonResponse readLesson(final Integer lessonId) {
        final Lesson lesson = lessonRepository.findById(lessonId)
            .orElseThrow(() -> new NotFoundException(ErrorCode.LESSON_NOT_FOUND));

        return LessonResponse.ofCreate(
            lesson.getId(),
            lesson.getLocation().getId(),
            lesson.getTeacher().getId(),
            lesson.getTitle(),
            enrollmentRepository.countByLessonId(lessonId),
            lesson.getDescription(),
            lesson.getAmount(),
            lesson.getMinStudent(),
            lesson.getMaxStudent(),
            lesson.getStartDate(),
            lesson.getEndDate(),
            lesson.getDeadline(),
            lesson.getStatus().name(),
            lesson.getSchoolLevel().name(),
            lesson.getSubject().name()
        );
    }

    /**
     * 전체 수업 목록 페이지 조회
     *
     * @param pageNumber 조회할 페이지 번호
     * @param pageSize   페이지 당 수업 개수
     * @return 수업 목록 응답 페이지
     * @author Hong
     */
    @Transactional(readOnly = true)
    public Page<LessonResponse> readLessons(
        final Integer pageNumber,
        final Integer pageSize
    ) {
        return new PageImpl<>(
            lessonRepository
                .findLessonsByPageSize(pageSize, pageNumber)
                .stream()
                .map(lesson -> {
                    int currStudent = enrollmentRepository.countByLessonId(lesson.getId());
                    return LessonResponse.ofCreatePage(lesson, currStudent);
                })
                .toList()
        );
    }

    /**
     * 수업 검색
     *
     * @param searchLessonRequest 검색 조건(키워드, 페이지 정보 등)
     * @return 검색된 수업 목록 페이지
     * @author Hong
     */
    @Transactional(readOnly = true)
    public Page<LessonResponse> searchLessons(
        final SearchLessonRequest searchLessonRequest
    ) {
        long totalLessonCount = lessonRepository.getTotalLessonCount(
            searchLessonRequest.keyword(),
            searchLessonRequest.schoolLevel(),
            searchLessonRequest.subject()
        );

        return new PageImpl<>(
            lessonRepository
                .searchLessons(
                    searchLessonRequest.keyword(),
                    searchLessonRequest.pageSize(),
                    searchLessonRequest.pageNumber(),
                    searchLessonRequest.schoolLevel(),
                    searchLessonRequest.subject()
                )
                .stream()
                .map(lesson -> {
                    int currStudent = enrollmentRepository.countByLessonId(lesson.getId());
                    return LessonResponse.ofCreatePage(lesson, currStudent);
                })
                .toList(),
            PageRequest.of(
                searchLessonRequest.pageNumber() - 1,
                searchLessonRequest.pageSize()
            ),
            totalLessonCount
        );
    }

    /**
     * 전체 페이지 수 반환
     *
     * @return 페이지 수를 포함한 응답 DTO
     * @author Hong
     */
    @Transactional(readOnly = true)
    public PageInfoResponse getTotalPages() {
        return new PageInfoResponse(lessonRepository.getTotalLessonPages(PAGE_SIZE, null));
    }

    /**
     * 로그인한 사용자의 역할에 따른 수업 목록 조회 - 학생: 수강 중인 수업 - 선생님: 내가 등록한 수업
     *
     * @param pageNumber 페이지 번호
     * @param pageSize   페이지 크기
     * @param accessor   로그인 사용자 정보
     * @return 수업 목록 페이지
     * @author Hong
     */
    @Transactional(readOnly = true)
    public Page<LessonResponse> getMyEnrolledLessons(
        final Integer pageNumber,
        final Integer pageSize,
        final Accessor accessor
    ) {
        return switch (accessor.getUserType()) {
            case STUDENT -> getLessonsForStudent(pageNumber, pageSize, accessor);
            case TEACHER -> getLessonsForTeacher(pageNumber, pageSize, accessor);
            case GUEST -> throw new BusinessException(ErrorCode.AUTH_FORBIDDEN);
        };
    }

    /**
     * 학생이 수강 중인 수업 목록 조회
     *
     * @param pageNumber 페이지 번호
     * @param pageSize   페이지 크기
     * @param accessor   로그인 사용자 정보
     * @return 수강 중인 수업 목록 페이지
     * @author Hong
     */
    private Page<LessonResponse> getLessonsForStudent(
        final Integer pageNumber,
        final Integer pageSize,
        final Accessor accessor
    ) {
        final Student student = studentRepository.findByPublicId(accessor.getPublicId())
            .orElseThrow(() -> new NotFoundException(STUDENT_NOT_FOUND));

        final Long totalStudent = enrollmentRepository.countByStudentId(student.getId());

        final List<LessonResponse> enrolledList = enrollmentRepository.
            getPagesByStudentId(student.getId()
                , pageNumber,
                pageSize)
            .stream()
            .map(enrollment -> {
                Lesson lesson = enrollment.getLesson();
                int currStudent = enrollmentRepository.countByLessonId(lesson.getId());
                return LessonResponse.ofCreatePage(lesson, currStudent);
            })
            .toList();

        return new PageImpl<>(
            enrolledList,
            PageRequest.of(pageNumber - 1, pageSize),
            totalStudent
        );
    }

    /**
     * 선생님이 개설한 수업 목록 조회
     *
     * @param pageNumber 페이지 번호
     * @param pageSize   페이지 크기
     * @param accessor   로그인 사용자 정보
     * @return 내가 등록한 수업 목록 페이지
     * @author Hong
     */
    private Page<LessonResponse> getLessonsForTeacher(
        final Integer pageNumber,
        final Integer pageSize,
        final Accessor accessor
    ) {
        final Teacher teacher = teacherRepository.findByPublicId(accessor.getPublicId())
            .orElseThrow(() -> new NotFoundException(TEACHER_NOT_FOUND));

        final Long TotalLessons = lessonRepository.getTotalLessenByTeacherId(teacher.getId());

        final List<LessonResponse> teachingLessons = lessonRepository.findAllByTeacherId(
                teacher.getId(), pageSize, pageNumber
            )
            .stream()
            .map(lesson -> {
                int currStudent = enrollmentRepository.countByLessonId(lesson.getId());
                return LessonResponse.ofCreatePage(lesson, currStudent);
            })
            .toList();

        return new PageImpl<>(
            teachingLessons,
            PageRequest.of(pageNumber - 1, pageSize),
            TotalLessons
        );
    }
}
