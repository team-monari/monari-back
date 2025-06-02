package com.monari.monariback.batch.itemlistener;

import com.monari.monariback.enrollment.entity.Enrollment;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ItemProcessListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CustomItemProcessListener implements ItemProcessListener<Enrollment, MimeMessage> {

    @Override
    public void onProcessError(Enrollment item, Exception ex) {
        log.error("Error occurred while processing enrollment with ID {}: {}", item.getId(), ex.getMessage(), ex);
    }
}
