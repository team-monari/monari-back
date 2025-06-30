package com.monari.monariback.batch.util;

import com.monari.monariback.enrollment.entity.Enrollment;
import com.monari.monariback.enrollment.entity.enumerated.EnrollmentStatus;
import com.monari.monariback.lesson.entity.enurmerated.LessonStatus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class LessonNoticeMessageBuilders {

    private final Map<LessonNoticeMessageBuilderType, LessonNoticeMessageBuilder> lessonNoticeMessageBuilderMap;

    public LessonNoticeMessageBuilders(List<LessonNoticeMessageBuilder> lessonNoticeMessageBuilders) {
        this.lessonNoticeMessageBuilderMap = lessonNoticeMessageBuilders.stream()
                .collect(Collectors.toMap(
                        LessonNoticeMessageBuilder::getLessonNoticeMessageBuilderType,
                        Function.identity()
                ));
    }

    public LessonNoticeMessageBuilder getLessonNoticeMessageBuilder(Enrollment enrollment) {
        LessonStatus lessonStatus = enrollment.getLesson().getStatus();
        EnrollmentStatus enrollmentStatus = enrollment.getStatus();

        LessonNoticeMessageBuilderType lessonNoticeMessageBuilderType = LessonNoticeMessageBuilderType.from(lessonStatus, enrollmentStatus);
        LessonNoticeMessageBuilder found = lessonNoticeMessageBuilderMap.get(lessonNoticeMessageBuilderType);
        if (found == null) {
            throw new IllegalArgumentException("지원하지 않는 수업 상태와 수강 상태입니다: " + lessonNoticeMessageBuilderType);
        }

        return found;
    }
}
