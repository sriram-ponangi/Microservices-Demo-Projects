package com.example.batch.demo.taskletSequentialJobflow.config.job;

import com.example.batch.demo.configs.JobConfig;
import com.example.batch.demo.taskletSequentialJobflow.config.step.StepSequentialConfigFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Primary
@Configuration("JobSequentialConfigImpl")
@Slf4j
public class JobSequentialConfigImpl implements JobConfig {
    public final StepSequentialConfigFactory stepConfigFactory;
    private final JobBuilderFactory jobBuilderFactory;

    public JobSequentialConfigImpl(StepSequentialConfigFactory stepConfigFactory,
                                   JobBuilderFactory jobBuilderFactory) {
        this.stepConfigFactory = stepConfigFactory;
        this.jobBuilderFactory = jobBuilderFactory;
    }

    @Override
    public Job mySimpleJob() {
        return jobBuilderFactory.get("MySimpleJob")
                .start(stepConfigFactory.createStepSequentialConfig1().myTaskletStep())
                .next(stepConfigFactory.createStepSequentialConfig2().myTaskletStep())
                .next(stepConfigFactory.createStepSequentialConfig3().myTaskletStep())
                .build();
    }
}
