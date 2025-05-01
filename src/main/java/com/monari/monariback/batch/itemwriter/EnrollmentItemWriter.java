package com.monari.monariback.batch.itemwriter;

import com.monari.monariback.enrollment.entity.Enrollment;
import com.monari.monariback.lesson.entity.Lesson;
import com.monari.monariback.lesson.entity.enurmerated.LessonStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class EnrollmentItemWriter implements ItemWriter<Lesson> {

    /**
     * 수업 상태 확정 및 최종 수강료 계산
     * 수강 신청 건마다 최종 수강료를 DB에 기록
     * @param chunk of Lesson
     */
    @Override
    public void write(Chunk<? extends Lesson> chunk) throws Exception {
        for (Lesson lesson : chunk) {
            lesson.updateStatusIfMinEnrollmentNotMet();

            if (lesson.getStatus() == LessonStatus.ACTIVE) {
                int finalPrice = lesson.calculateFinalPrice();
                List<Enrollment> enrollments = lesson.getEnrollments();

                for (Enrollment enrollment : enrollments) {
                    enrollment.updateFinalPrice(finalPrice);
                }
            }
        }
    }
}
