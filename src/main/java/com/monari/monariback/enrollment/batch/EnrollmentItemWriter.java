package com.monari.monariback.enrollment.batch;

import com.monari.monariback.enrollment.entity.Enrollment;
import com.monari.monariback.enrollment.repository.EnrollmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

@RequiredArgsConstructor
public class EnrollmentItemWriter implements ItemWriter<LessonFeeDto> {

    private final EnrollmentRepository enrollmentRepository;

    /**
     * 수강 신청 건마다 최종 수강료를 DB에 기록
     * @param chunk of LessonFeeDto - 쓰기 작업의 대상이 될 수업들
     */
    @Override
    public void write(Chunk<? extends LessonFeeDto> chunk) throws Exception {
        for (LessonFeeDto dto : chunk) {
            List<Enrollment> enrollments = enrollmentRepository.findAllByLessonId(dto.lessonId());

            for (Enrollment enrollment : enrollments) {
                enrollment.updateFinalPrice(dto.finalPrice());
            }
        }

    }
}
