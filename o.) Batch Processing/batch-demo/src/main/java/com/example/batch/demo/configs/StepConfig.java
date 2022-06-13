package com.example.batch.demo.configs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class StepConfig {

    private final StepBuilderFactory stepBuilderFactory;

    public StepConfig(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean
    public Step myStep() {
        return stepBuilderFactory.get("myStep").tasklet((stepContribution, chunkContext) -> {
                    log.info("My-Step Executed - {}", System.currentTimeMillis());
                    return RepeatStatus.FINISHED;
                })
                .build();
    }
}
