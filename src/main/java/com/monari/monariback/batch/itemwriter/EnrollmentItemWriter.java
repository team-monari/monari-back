package com.monari.monariback.batch.itemwriter;

import com.monari.monariback.enrollment.entity.Enrollment;
import com.monari.monariback.enrollment.entity.enumerated.EnrollmentStatus;
import com.monari.monariback.enrollment.repository.EnrollmentRepository;
import com.monari.monariback.lesson.entity.Lesson;
import com.monari.monariback.lesson.entity.enurmerated.LessonStatus;
import com.monari.monariback.lesson.repository.LessonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class EnrollmentItemWriter implements ItemWriter<Lesson> {

    private final EnrollmentRepository enrollmentRepository;
    private final LessonRepository lessonRepository;

    /**
     * 수업 상태 확정 및 최종 수강료 계산
     * 수강 신청 건마다 최종 수강료를 DB에 기록
     * @param chunk of Lesson
     */
    @Override
    public void write(Chunk<? extends Lesson> chunk) throws Exception {
        List<Lesson> lessonsCancelled = new ArrayList<>();

        for (Lesson lesson : chunk) {
            if (lesson.isMinEnrollmentNotMet() || lesson.getStatus() == LessonStatus.CANCELED) {
                lessonsCancelled.add(lesson);
            } else {

                int finalPrice = lesson.calculateFinalPrice();
                List<Integer> enrollmentIds = lesson.getEnrollments().stream()
                        .filter(e -> e.getStatus() == EnrollmentStatus.PENDING)
                        .map(Enrollment::getId)
                        .toList();

                enrollmentRepository.updateFinalPrice(enrollmentIds, finalPrice, EnrollmentStatus.REQUESTED);
            }
        }

        List<Integer> lessonIds = lessonsCancelled.stream().map(Lesson::getId).toList();
        lessonRepository.updateStatus(lessonIds, LessonStatus.CANCELED);
    }
}
