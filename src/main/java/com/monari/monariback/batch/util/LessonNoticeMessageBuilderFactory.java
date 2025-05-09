package com.monari.monariback.batch.util;

import com.monari.monariback.common.exception.BusinessException;
import com.monari.monariback.enrollment.entity.Enrollment;
import com.monari.monariback.enrollment.entity.enumerated.EnrollmentStatus;
import com.monari.monariback.lesson.entity.enurmerated.LessonStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.monari.monariback.common.error.ErrorCode.LESSON_NOT_SUPPORTED_STATUS;

@Component
@RequiredArgsConstructor
public class LessonNoticeMessageBuilderFactory {

    private final LessonFeeNoticeMessageBuilder lessonFeeNoticeMessageBuilder;
    private final LessonCancelNoticeMessageBuilder lessonCancelNoticeMessageBuilder;

    public LessonNoticeMessageBuilder getLessonNoticeMessageBuilder(Enrollment enrollment) {
        LessonStatus lessonStatus = enrollment.getLesson().getStatus();
        EnrollmentStatus enrollmentStatus = enrollment.getStatus();

        if (lessonStatus == LessonStatus.ACTIVE && enrollmentStatus == EnrollmentStatus.REQUESTED) {
            return lessonFeeNoticeMessageBuilder;
        }
        if (lessonStatus == LessonStatus.CANCELED && enrollmentStatus == EnrollmentStatus.PENDING) {
            return lessonCancelNoticeMessageBuilder;
        }

        throw new BusinessException(LESSON_NOT_SUPPORTED_STATUS);
    }
}
