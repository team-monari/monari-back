package com.monari.monariback.lesson.service;

import com.monari.monariback.enrollment.repository.EnrollmentRepository;
import com.monari.monariback.global.config.error.ErrorCode;
import com.monari.monariback.global.config.error.exception.NotFoundException;
import com.monari.monariback.lesson.dto.request.CreateLessonRequest;
import com.monari.monariback.lesson.dto.request.UpdateLessonRequest;
import com.monari.monariback.lesson.dto.response.LessonResponse;
import com.monari.monariback.lesson.dto.response.PageInfoResponse;
import com.monari.monariback.lesson.entity.Lesson;
import com.monari.monariback.lesson.repository.LessonRepository;
import com.monari.monariback.location.entity.Location;
import com.monari.monariback.location.repository.LocationRepository;
import com.monari.monariback.teacher.domain.Teacher;
import com.monari.monariback.teacher.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class LessonService {

    // TODO : 선생님아이디를 JWT 토큰에서 추출, teacher Repository에서 선생님 정보 가져오기
    private static final Integer PAGE_SIZE = 6;

    private final LessonRepository lessonRepository;

    private final LocationRepository locationRepository;

    private final TeacherRepository teacherRepository;
    private final EnrollmentRepository enrollmentRepository;

    /**
     * 수업 생성
     *
     * @param lessonDto - 수업 생성 정보가 들어간 dto 입니다.
     * @return CreateLessonResponse - 수업 생성 후 응답 dto 입니다.
     * @author Hong
     */
    public ResponseEntity<String> create(final CreateLessonRequest lessonDto) {

        Location location = locationRepository.findById(lessonDto.locationId()).orElseThrow(
            () -> new NotFoundException(ErrorCode.LOCATION_NOT_FOUND)
        );
        Teacher teacher = teacherRepository.findById(lessonDto.teacherId())
            .orElseThrow(() -> new NotFoundException(ErrorCode.BAD_REQUEST));

        Lesson lesson = Lesson.ofCreate(
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
        return ResponseEntity.ok("생성에 성공하였습니다");
    }

    /**
     * 수업 수정
     *
     * @param lessonId  - 수정할 수업의 id
     * @param lessonDto - 수정할 수업 정보가 담긴 dto
     * @return LessonResponse - 수정된 수업 정보가 담긴 dto
     * @author Hong
     */
    public ResponseEntity<String> update(final Integer lessonId,
        final UpdateLessonRequest lessonDto) {
        Lesson lesson = lessonRepository.findById(lessonId).orElseThrow(
            () -> new NotFoundException(ErrorCode.LESSON_NOT_FOUND)
        );

        Location location = locationRepository.findById(lessonDto.locationId()).orElseThrow(() ->
            new NotFoundException(ErrorCode.LOCATION_NOT_FOUND)
        );

        lesson.update(
            location,
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

        return ResponseEntity.ok("수정이 완료되었습니다.");
    }

    /**
     * 수업 조회
     *
     * @param lessonId - 조회할 수업의 id
     * @return LessonResponse - 조회된 수업 정보가 담긴 dto
     * @author Hong
     */
    @Transactional(readOnly = true)
    public LessonResponse read(final Integer lessonId) {
        final Lesson lesson = lessonRepository.findById(lessonId).orElseThrow(
            () -> new NotFoundException(ErrorCode.LESSON_NOT_FOUND)
        );

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

    @Transactional(readOnly = true)
    public Page<LessonResponse> searchLessons(
        final String keyword,
        final Integer pageNumber,
        final Integer pageSize
    ) {
        return new PageImpl<>(
            lessonRepository
                .searchLessons(keyword, pageSize, pageNumber)
                .stream()
                .map(lesson -> {
                    int currStudent = enrollmentRepository.countByLessonId(lesson.getId());
                    return LessonResponse.ofCreatePage(lesson, currStudent);
                })
                .toList()
        );
    }

    public PageInfoResponse getTotalPages() {
        int totalPages = lessonRepository.getTotalLessonPages(PAGE_SIZE);
        return new PageInfoResponse(totalPages);
    }
}
