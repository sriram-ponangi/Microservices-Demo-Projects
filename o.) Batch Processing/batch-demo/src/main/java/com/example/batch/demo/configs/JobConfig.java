package com.example.batch.demo.configs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class JobConfig {
    public final StepConfig stepConfig;

    public JobConfig(StepConfig stepConfig) {
        this.stepConfig = stepConfig;
    }

    @Bean
    public Job myJob(JobBuilderFactory jobBuilderFactory) {
        Step s = stepConfig.myStep();
        log.info("Step Object = {}", s.hashCode());

        return jobBuilderFactory.get("myJob")
                .start(stepConfig.myStep())
                .build();
    }

    @Bean
    public Job myJob2(JobBuilderFactory jobBuilderFactory) {
        Step s2 = stepConfig.myStep();
        log.info("Step Object = {}", s2.hashCode());
        return jobBuilderFactory.get("myJob2")
                .start(stepConfig.myStep())
                .build();
    }
}
