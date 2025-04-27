package com.monari.monariback.batch.scheduler;

import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OpenApiJonScheduler {

    private final JobLauncher jobLauncher;
    private final Job publicDataSyncJob;

    public OpenApiJonScheduler(JobLauncher jobLauncher,
        @Qualifier("publicDataSyncJob") Job publicDataSyncJob) {
        this.jobLauncher = jobLauncher;
        this.publicDataSyncJob = publicDataSyncJob;
    }

    @Scheduled(cron = "0 25 8,20 * * *", zone = "Asia/Seoul")
    public void runPublicDataJob() throws Exception {
        JobParameters jobParameters = new JobParametersBuilder()
            .addString("openApiStartDate", UUID.randomUUID().toString())
            .toJobParameters();

        try {
            jobLauncher.run(publicDataSyncJob, jobParameters);
        } catch (Exception e) {
            log.error("publicDataSyncJob failed", e);
        }
    }
}
