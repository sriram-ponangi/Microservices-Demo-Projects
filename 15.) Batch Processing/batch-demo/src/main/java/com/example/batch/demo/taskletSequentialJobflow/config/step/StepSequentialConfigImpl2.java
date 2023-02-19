package com.example.batch.demo.taskletSequentialJobflow.config.step;

import com.example.batch.demo.configs.StepConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service("StepSequentialConfigImpl2")
@Slf4j
public class StepSequentialConfigImpl2 implements StepConfig {
    private final StepBuilderFactory stepBuilderFactory;

    public StepSequentialConfigImpl2(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Override
    public Step myTaskletStep() {
        return stepBuilderFactory.get("myStep-2").tasklet((stepContribution, chunkContext) -> {
            log.info("Job Execution Context : {}", chunkContext.getStepContext()
                    .getJobExecutionContext().keySet()
                    .stream()
                    .map(key -> key.toString() + " : " + chunkContext
                            .getStepContext()
                            .getJobExecutionContext()
                            .getOrDefault(key, "UNKNOWN"))
                    .collect(Collectors.joining("\n"))
            );
            log.info("My-Step Executed - {}", System.currentTimeMillis());
            return RepeatStatus.FINISHED;
        }).build();
    }
}
