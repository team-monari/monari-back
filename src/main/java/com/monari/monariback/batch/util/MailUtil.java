package com.monari.monariback.batch.util;

import com.monari.monariback.batch.constant.EnrollmentMailConstants;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import static com.monari.monariback.batch.constant.EnrollmentMailConstants.LESSON_FEE_NOTICE_MAIL_TEMPLATE;

@Component
@RequiredArgsConstructor
public class MailUtil {

    private final JavaMailSender sender;
    private final TemplateEngine templateEngine;

    public MimeMessage buildLessonFeeNoticeMessage(String email, Context context) throws MessagingException {
        MimeMessageHelper helper = prepareServiceMail();
        String htmlContent = templateEngine.process(LESSON_FEE_NOTICE_MAIL_TEMPLATE, context);
        helper.setText(htmlContent, true);
        helper.setSubject(EnrollmentMailConstants.LESSON_FEE_NOTICE_MAIL_SUBJECT);
        helper.setTo(email);

        return helper.getMimeMessage();
    }

    private MimeMessageHelper prepareServiceMail() throws MessagingException {
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom(EnrollmentMailConstants.SERVICE_MAIL_ADDRESS);

        return helper;
    }
}
