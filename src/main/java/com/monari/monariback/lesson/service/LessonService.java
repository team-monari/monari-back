package com.monari.monariback.lesson.service;

import com.monari.monariback.lesson.dto.request.CreateLessonRequest;
import com.monari.monariback.lesson.dto.request.UpdateLessonRequest;
import com.monari.monariback.lesson.dto.response.LessonResponse;
import com.monari.monariback.lesson.entity.Lesson;
import com.monari.monariback.lesson.repository.LessonRepository;
import com.monari.monariback.location.entity.Location;
import com.monari.monariback.location.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class LessonService {

    // TODO : 선생님아이디를 JWT 토큰에서 추출, teacher Repository에서 선생님 정보 가져오기
    private static final Integer teacherId = 1;

    private final LessonRepository lessonRepository;

    private final LocationRepository locationRepository;

    /**
     * 수업 생성
     *
     * @param lessonDto - 수업 생성 정보가 들어간 dto 입니다.
     * @return CreateLessonResponse - 수업 생성 후 응답 dto 입니다.
     * @author Hong
     */
    public ResponseEntity<?> create(final CreateLessonRequest lessonDto) {

        Location location = locationRepository.findById(lessonDto.locationId()).orElseThrow(
            () -> new IllegalArgumentException("해당 장소가 존재하지 않습니다.") // TODO : 커스텀 예외 처리
        );

        Lesson lesson = Lesson.ofCreate(
            location,
            teacherId,
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
        // 이렇게 String 으로 해도 되는지 논의
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
    public ResponseEntity<?> update(final Integer lessonId, final UpdateLessonRequest lessonDto) {
        Lesson lesson = lessonRepository.findById(lessonId).orElseThrow(
            () -> new IllegalArgumentException("해당 수업이 존재하지 않습니다.") // TODO : 커스텀 예외 처리
        );

        Location location = locationRepository.findById(lessonDto.locationId()).orElseThrow(() ->
            new IllegalArgumentException("해당 장소가 존재하지 않습니다.") // TODO : 커스텀 예외 처리
        );

        lesson.update(
            location,
            lessonDto.teacherId(),
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
            () -> new IllegalArgumentException("해당 수업이 존재하지 않습니다.") // TODO : 커스텀 예외 처리
        );

        return new LessonResponse(
            lesson.getId(),
            lesson.getLocation().getId(),
            lesson.getTeacherId(),
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
}
