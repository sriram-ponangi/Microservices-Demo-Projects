package com.example.batch.demo.taskletConditionalJobflow.config.job;

import com.example.batch.demo.configs.JobConfig;
import com.example.batch.demo.taskletConditionalJobflow.config.step.StepConditionalConfigFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration("JobConditionalConfigImpl")
@Slf4j
public class JobConditionalConfigImpl implements JobConfig {
    public final StepConditionalConfigFactory stepConfigFactory;
    private final JobBuilderFactory jobBuilderFactory;

    public JobConditionalConfigImpl(StepConditionalConfigFactory stepConfigFactory,
                                    JobBuilderFactory jobBuilderFactory) {
        this.stepConfigFactory = stepConfigFactory;
        this.jobBuilderFactory = jobBuilderFactory;
    }

    @Override
    public Job mySimpleJob() {
        Step step1 = stepConfigFactory.createStepConditionalConfig1().myTaskletStep();
        return jobBuilderFactory.get("MyConditionalJob")
                .start(step1)
                .on("FAILED")
                    .to(stepConfigFactory.createStepConditionalConfig3().myTaskletStep())

                .from(step1)
                    .on("*")
                    .to(stepConfigFactory.createStepConditionalConfig2().myTaskletStep())
                .end()
                .build();
    }
}
