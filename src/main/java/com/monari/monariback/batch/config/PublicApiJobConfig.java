package com.monari.monariback.batch.config;

import com.monari.monariback.batch.itemReader.PublicApiDataReader;
import com.monari.monariback.batch.itemprocessor.PublicApiDataProcessor;
import com.monari.monariback.batch.itemwriter.PublicApiDataWriter;
import com.monari.monariback.location.dto.OpenApiLocationDto;
import com.monari.monariback.location.entity.Location;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing(tablePrefix = "batch.BATCH_")
@RequiredArgsConstructor
public class PublicApiJobConfig {


    private final PublicApiDataReader publicApiDataReader;
    private final PublicApiDataWriter publicApiDataWriter;
    private final PublicApiDataProcessor publicApiDataProcessor;

    @Bean(name = "publicDataSyncJob")
    public Job publicDataSyncJob(JobRepository jobRepository,
        @Qualifier("publicDataStep") Step getApiData) {
        return new JobBuilder("publicDataSyncJob", jobRepository)
            .start(getApiData)
            .build();
    }

    @Bean
    public Step publicDataStep(JobRepository jobRepository,
        PlatformTransactionManager transactionManager) {
        return new StepBuilder("publicDataStep", jobRepository)
            .<OpenApiLocationDto, Location>chunk(10, transactionManager)
            .reader(publicApiDataReader)
            .processor(publicApiDataProcessor)
            .writer(publicApiDataWriter)
            .build();
    }
}
