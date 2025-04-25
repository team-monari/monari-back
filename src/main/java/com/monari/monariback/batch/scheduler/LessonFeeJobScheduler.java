package com.monari.monariback.batch.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Slf4j
@Component
@RequiredArgsConstructor
public class LessonFeeJobScheduler {

    private final JobLauncher jobLauncher;
    private final Job lessonFeeJob;

    /**
     * 최종 수강료 계산 후 DB 쓰기 작업 및 수강료 안내 메일 전송 배치 작업 스케줄링
     * 매일 24시 10분 수업 시작일이 된 수업을 대상으로 진행
     */
    @Scheduled(cron = "0 10 0 * * *", zone = "Asia/Seoul")
    public void launchLessonFeeJob() throws Exception {
        JobParameters jobParameters = new JobParametersBuilder()
                .addLocalDate("lessonStartDate", LocalDate.now())
                .toJobParameters();

        try {
            jobLauncher.run(lessonFeeJob, jobParameters);
        } catch (Exception e) {
            log.error("LessonFeeJob failed", e);
        }
    }
}
