package com.monari.monariback.batch.itemprocessor;

import com.monari.monariback.batch.util.LessonNoticeMessageBuilder;
import com.monari.monariback.batch.util.LessonNoticeMessageBuilders;
import com.monari.monariback.enrollment.entity.Enrollment;
import com.monari.monariback.enrollment.entity.enumerated.EnrollmentStatus;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MailItemProcessor implements ItemProcessor<Enrollment, MimeMessage> {

    private final LessonNoticeMessageBuilders lessonNoticeMessageBuilders;

    /**
     * 수강 신청한 학생들에게 보낼 수강료 안내 메일 메시지 구성
     * @param enrollment
     * @return MimeMessage - 수강 신청 상태 REQUESTED or PENDING인 경우
     *         null - 그외 수강 신청 상태 경우  (해당 enrollment의 배치 작업은 더 이상 진행되지 않음)
     */
    @Override
    public MimeMessage process(Enrollment enrollment) throws Exception {
        EnrollmentStatus enrollmentStatus = enrollment.getStatus();
        if (enrollmentStatus != EnrollmentStatus.REQUESTED && enrollmentStatus != EnrollmentStatus.PENDING) {
            return null;
        }

        LessonNoticeMessageBuilder lessonNoticeMessageBuilder =
                lessonNoticeMessageBuilders.getLessonNoticeMessageBuilder(enrollment);

        return lessonNoticeMessageBuilder.buildNoticeMessage(enrollment);
    }

}
