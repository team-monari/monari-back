package com.monari.monariback.batch.config;

import com.monari.monariback.batch.dto.LessonFeeDto;
import com.monari.monariback.batch.itemprocessor.LessonItemProcessor;
import com.monari.monariback.batch.itemprocessor.MailItemProcessor;
import com.monari.monariback.batch.itemwriter.EnrollmentItemWriter;
import com.monari.monariback.enrollment.entity.Enrollment;
import com.monari.monariback.lesson.entity.Lesson;
import jakarta.mail.internet.MimeMessage;
import jakarta.persistence.EntityManagerFactory;
import java.time.LocalDate;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.batch.item.mail.javamail.MimeMessageItemWriter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing(tablePrefix = "batch.BATCH_")
@RequiredArgsConstructor
public class LessonFeeJobConfig {

    private final LessonItemProcessor lessonItemProcessor;
    private final MailItemProcessor mailItemProcessor;
    private final EnrollmentItemWriter enrollmentItemWriter;

    @Bean
    @StepScope
    public JpaPagingItemReader<Lesson> lessonItemReader(
        @Value("#{jobParameters[lessonStartDate]}") LocalDate lessonStartDate,
        EntityManagerFactory entityManagerFactory) {
        Map<String, Object> params = Map.of("lessonStartDate", lessonStartDate);

        return new JpaPagingItemReaderBuilder<Lesson>().name("lessonItemReader")
            .entityManagerFactory(entityManagerFactory)
            .queryString("""
                SELECT l
                FROM Lesson l
                JOIN l.enrollments e
                WHERE l.startDate = :lessonStartDate
                """)
            .parameterValues(params)
            .pageSize(10)
            .build();
    }

    @Bean
    public Step lessonFeeCalculationStep(JobRepository jobRepository,
        PlatformTransactionManager transactionManager,
        JpaPagingItemReader<Lesson> lessonItemReader) {
        return new StepBuilder("lessonFeeCalculationStep", jobRepository)
            .<Lesson, LessonFeeDto>chunk(10, transactionManager)
            .reader(lessonItemReader)
            .processor(lessonItemProcessor)
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
                """)
            .parameterValues(params)
            .pageSize(10)
            .build();
    }

    @Bean
    public MimeMessageItemWriter mimeMessageItemWriter(JavaMailSender mailSender) {
        MimeMessageItemWriter mimeMessageItemWriter = new MimeMessageItemWriter();
        mimeMessageItemWriter.setJavaMailSender(mailSender);
        return mimeMessageItemWriter;
    }

    @Bean
    public Step lessonFeeNotificationStep(JobRepository jobRepository,
        PlatformTransactionManager transactionManager,
        JpaPagingItemReader<Enrollment> enrollmentItemReader,
        MimeMessageItemWriter mimeMessageItemWriter) {
        return new StepBuilder("lessonFeeNotificationStep", jobRepository)
            .<Enrollment, MimeMessage>chunk(10, transactionManager)
            .reader(enrollmentItemReader)
            .processor(mailItemProcessor)
            .writer(mimeMessageItemWriter)
            .build();
    }

    @Bean(name = "lessonFeeJob")
    public Job lessonFeeJob(JobRepository jobRepository,
        @Qualifier("lessonFeeCalculationStep") Step lessonFeeCalculationStep,
        @Qualifier("lessonFeeNotificationStep") Step lessonFeeNotificationStep) {
        return new JobBuilder("lessonFeeJob", jobRepository)
            .start(lessonFeeCalculationStep)
            .next(lessonFeeNotificationStep)
            .build();
    }


}
