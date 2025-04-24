package com.monari.monariback.batch.itemprocessor;

import com.monari.monariback.batch.util.MailUtil;
import com.monari.monariback.batch.dto.LessonFeeNoticeMailContextDto;
import com.monari.monariback.enrollment.entity.Enrollment;
import com.monari.monariback.enrollment.entity.enumerated.EnrollmentStatus;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;

@Component
@RequiredArgsConstructor
public class MailItemProcessor implements ItemProcessor<Enrollment, MimeMessage> {

    private final MailUtil mailUtil;

    /**
     * 수강 신청한 학생들에게 보낼 수강료 안내 메일 메시지 구성
     * @param enrollment
     * @return MimeMessage - 수강 신청 상태 REQUESTED인 경우
     *         null - 그외 수강 신청 상태 경우  (해당 enrollment의 배치 작업은 더 이상 진행되지 않음)
     */
    @Override
    public MimeMessage process(Enrollment enrollment) throws Exception {
        if (enrollment.getStatus() != EnrollmentStatus.REQUESTED) {
            return null;
        }

        String email = enrollment.getStudent().getEmail();
        Context context = createContext(enrollment);

        return mailUtil.buildLessonFeeNoticeMessage(email, context);
    }

    private static Context createContext(Enrollment enrollment) {
        Context context = new Context();
        context.setVariable("mailContextDto", LessonFeeNoticeMailContextDto.from(enrollment));
        return context;
    }

}
