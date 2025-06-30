package com.monari.monariback.batch.util;

import com.monari.monariback.enrollment.entity.Enrollment;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;

import static com.monari.monariback.common.constant.MailConstants.SERVICE_MAIL_ADDRESS;

@Component
@RequiredArgsConstructor
public abstract class LessonNoticeMessageBuilder {

    private final JavaMailSender sender;

    public abstract MimeMessage buildNoticeMessage(Enrollment enrollment) throws MessagingException;

    public abstract Context createContext(Enrollment enrollment);

    public abstract LessonNoticeMessageBuilderType getLessonNoticeMessageBuilderType();

    public MimeMessageHelper prepareServiceMail() throws MessagingException {
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom(SERVICE_MAIL_ADDRESS);

        return helper;
    }

}
