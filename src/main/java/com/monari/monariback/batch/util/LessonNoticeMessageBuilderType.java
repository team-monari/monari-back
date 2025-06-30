package com.monari.monariback.batch.util;

import com.monari.monariback.enrollment.entity.enumerated.EnrollmentStatus;
import com.monari.monariback.lesson.entity.enurmerated.LessonStatus;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
public enum LessonNoticeMessageBuilderType {

    LESSON_FEE_NOTICE(LessonStatus.ACTIVE, EnrollmentStatus.REQUESTED),
    LESSON_CANCEL_NOTICE(LessonStatus.CANCELED, EnrollmentStatus.PENDING),
    ;

    private final LessonStatus lessonStatus;
    private final EnrollmentStatus enrollmentStatus;

    public static LessonNoticeMessageBuilderType from(LessonStatus lessonStatus, EnrollmentStatus enrollmentStatus) {
        return Arrays.stream(values())
                .filter(e -> e.lessonStatus == lessonStatus && e.enrollmentStatus == enrollmentStatus)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("지원하지 않는 상태 조합입니다: " + lessonStatus + ", " + enrollmentStatus));
    }
}
