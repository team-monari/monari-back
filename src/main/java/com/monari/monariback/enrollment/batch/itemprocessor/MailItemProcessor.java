package com.monari.monariback.enrollment.batch.itemprocessor;

import com.monari.monariback.enrollment.entity.Enrollment;
import com.monari.monariback.enrollment.entity.enumerated.EnrollmentStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import static com.monari.monariback.enrollment.constant.EnrollmentBatchConstants.SERVICE_EMAIL_ADDRESS;

@Component
@RequiredArgsConstructor
public class MailItemProcessor implements ItemProcessor<Enrollment, SimpleMailMessage> {

    /**
     * 수강 신청한 학생들에게 보낼 수강료 안내 메일 메시지 구성
     * @param enrollment
     * @return SimpleMailMessage - 수강 신청 상태 REQUESTED인 경우
     *         null - 그외 수강 신청 상태 경우  (해당 enrollment의 배치 작업은 더 이상 진행되지 않음)
     */
    @Override
    public SimpleMailMessage process(Enrollment enrollment) throws Exception {
        SimpleMailMessage message = new SimpleMailMessage();
        String lessonTitle = enrollment.getLesson().getTitle();

        if (enrollment.getStatus() != EnrollmentStatus.REQUESTED) {
            return null;
        }

        message.setFrom(SERVICE_EMAIL_ADDRESS);
        message.setTo(enrollment.getStudent().getEmail());
        message.setSubject(lessonTitle + " 수강료 안내");
        message.setText(
                lessonTitle + "에 대한 수강료: " +
                enrollment.getFinalPrice() + " 를 내세요.");
        return message;
    }
}
