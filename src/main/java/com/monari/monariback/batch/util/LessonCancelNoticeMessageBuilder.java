package com.monari.monariback.batch.util;

import com.monari.monariback.batch.dto.LessonCancelNoticeContextDto;
import com.monari.monariback.enrollment.entity.Enrollment;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import static com.monari.monariback.batch.util.LessonNoticeMessageBuilderType.LESSON_CANCEL_NOTICE;

@Component
public class LessonCancelNoticeMessageBuilder extends LessonNoticeMessageBuilder {

    private static final String LESSON_CANCEL_NOTICE_MAIL_SUBJECT = "[모나리] 수업 취소 안내드립니다.";
    private static final String LESSON_CANCEL_NOTICE_MAIL_TEMPLATE = "lesson-cancel-notice-mail.html";
    private final TemplateEngine templateEngine;

    public LessonCancelNoticeMessageBuilder(JavaMailSender sender, TemplateEngine templateEngine) {
        super(sender);
        this.templateEngine = templateEngine;
    }

    @Override
    public MimeMessage buildNoticeMessage(Enrollment enrollment) throws MessagingException {
        MimeMessageHelper helper = prepareServiceMail();
        Context context = createContext(enrollment);
        String email = enrollment.getStudent().getEmail();

        String htmlContent = templateEngine.process(LESSON_CANCEL_NOTICE_MAIL_TEMPLATE, context);
        helper.setText(htmlContent, true);
        helper.setSubject(LESSON_CANCEL_NOTICE_MAIL_SUBJECT);
        helper.setTo(email);

        return helper.getMimeMessage();
    }

    @Override
    public Context createContext(Enrollment enrollment) {
        Context context = new Context();
        context.setVariable("mailContextDto", LessonCancelNoticeContextDto.from(enrollment));
        return context;
    }

    @Override
    public LessonNoticeMessageBuilderType getLessonNoticeMessageBuilderType() {
        return LESSON_CANCEL_NOTICE;
    }
}
