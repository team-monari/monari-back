package com.monari.monariback.batch.config;

import com.monari.monariback.batch.itemlistener.CustomItemProcessListener;
import com.monari.monariback.batch.itemprocessor.MailItemProcessor;
import com.monari.monariback.batch.itemwriter.EnrollmentItemWriter;
import com.monari.monariback.enrollment.entity.Enrollment;
import com.monari.monariback.lesson.entity.Lesson;
import com.monari.monariback.lesson.repository.LessonRepository;
import jakarta.mail.internet.MimeMessage;
import jakarta.persistence.EntityManagerFactory;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.batch.item.mail.javamail.MimeMessageItemWriter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.data.domain.Sort;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing(tablePrefix = "batch.BATCH_")
@RequiredArgsConstructor
public class LessonFeeJobConfig {
    private static final String JOB_NAME = "lessonFeeJob";
    private static final String CALCULATION_STEP_NAME = "lessonFeeCalculationStep";
    private static final String NOTIFICATION_STEP_NAME = "lessonFeeNotificationStep";

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    private final MailItemProcessor mailItemProcessor;
    private final EnrollmentItemWriter enrollmentItemWriter;
    private final LessonRepository lessonRepository;

    private final CustomItemProcessListener customItemProcessListener;

    @Value("${lessonChunkSize:10}")
    private int lessonChunkSize;

    @Value("${enrollmentChunkSize:5}")
    private int enrollmentChunkSize;
    @Value("${poolSize:5}")
    private int poolSize;

    @Bean
    @StepScope
    public RepositoryItemReader<Lesson> lessonItemReader(
        @Value("#{jobParameters[lessonStartDate]}") LocalDate lessonStartDate) {
        return new RepositoryItemReaderBuilder<Lesson>().name("lessonItemReader")
                .repository(lessonRepository)
                .methodName("findAllByStartDate")
                .arguments(lessonStartDate)
                .sorts(Collections.singletonMap("id", Sort.Direction.ASC))
                .pageSize(lessonChunkSize)
                .build();
    }

    @Bean(name = CALCULATION_STEP_NAME)
    public Step lessonFeeCalculationStep(RepositoryItemReader<Lesson> lessonItemReader) {
        return new StepBuilder(CALCULATION_STEP_NAME, jobRepository)
                .<Lesson, Lesson>chunk(lessonChunkSize, transactionManager)
                .reader(lessonItemReader)
                .writer(enrollmentItemWriter)
                .build();
    }

    @Bean
    @StepScope
    public JpaPagingItemReader<Enrollment> enrollmentItemReader(
        @Value("#{jobParameters[lessonStartDate]}") LocalDate lessonStartDate,
        EntityManagerFactory entityManagerFactory) {
        Map<String, Object> params = Map.of("lessonStartDate", lessonStartDate);

        return new JpaPagingItemReaderBuilder<Enrollment>().name("enrollmentItemReader")
                .entityManagerFactory(entityManagerFactory)
                .queryString("""
                    SELECT e
                    FROM Enrollment e
                    JOIN FETCH e.lesson l
                    JOIN FETCH l.teacher
                    JOIN FETCH e.student
                    WHERE l.startDate = :lessonStartDate
                    ORDER BY e.id
                    """)
                .parameterValues(params)
                .saveState(false)
                .pageSize(enrollmentChunkSize)
                .build();
    }

    @Bean
    public MimeMessageItemWriter mimeMessageItemWriter(JavaMailSender mailSender) {
        MimeMessageItemWriter mimeMessageItemWriter = new MimeMessageItemWriter();
        mimeMessageItemWriter.setJavaMailSender(mailSender);
        return mimeMessageItemWriter;
    }

    @Bean(name = NOTIFICATION_STEP_NAME)
    public Step lessonFeeNotificationStep(
        JpaPagingItemReader<Enrollment> enrollmentItemReader,
        MimeMessageItemWriter mimeMessageItemWriter) {
        return new StepBuilder(NOTIFICATION_STEP_NAME, jobRepository)
            .<Enrollment, MimeMessage>chunk(enrollmentChunkSize, transactionManager)
            .reader(enrollmentItemReader)
            .processor(mailItemProcessor)
            .writer(mimeMessageItemWriter)
            .taskExecutor(taskExecutor())
            .listener(customItemProcessListener)
            .build();
    }

    @Bean(name = JOB_NAME)
    public Job lessonFeeJob(
        @Qualifier(CALCULATION_STEP_NAME) Step lessonFeeCalculationStep,
        @Qualifier(NOTIFICATION_STEP_NAME) Step lessonFeeNotificationStep) {
        return new JobBuilder(JOB_NAME, jobRepository)
            .start(lessonFeeCalculationStep)
            .next(lessonFeeNotificationStep)
            .build();
    }

    @Bean
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(poolSize);
        executor.setMaxPoolSize(poolSize);
        executor.setThreadNamePrefix("batch-lesson-fee-");
        executor.setWaitForTasksToCompleteOnShutdown(Boolean.TRUE);
        executor.initialize();
        return executor;
    }

}
