package com.monari.monariback.enrollment.batch.itemprocessor;

import com.monari.monariback.common.exception.BusinessException;
import com.monari.monariback.enrollment.dto.LessonFeeDto;
import com.monari.monariback.enrollment.service.EnrollmentService;
import com.monari.monariback.lesson.entity.Lesson;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LessonItemProcessor implements ItemProcessor<Lesson, LessonFeeDto> {

    private final EnrollmentService enrollmentService;

    /**
     * 수업의 최종 수강료 계산
     * @param lesson
     * @return LessonFeeDto - 수강료 유효성 검증 및 계산 로직 성공적으로 처리 시
     *         null - 비즈니스 예외 발생 시 (해당 lesson의 배치 작업은 더 이상 진행되지 않음)
     */
    @Override
    public LessonFeeDto process(Lesson lesson) throws Exception {
        Integer lessonId = lesson.getId();
        try {
            int finalPrice = enrollmentService.decideFinalPrice(lessonId);
            return new LessonFeeDto(lessonId, finalPrice);
        } catch (BusinessException e) {
            return null;
        }
    }

}
