package com.example.batch.demo.taskletSequentialJobflow.config.step;

import com.example.batch.demo.configs.StepConfig;

public interface StepSequentialConfigFactory {
    StepConfig createStepSequentialConfig1();
    StepConfig createStepSequentialConfig2();
    StepConfig createStepSequentialConfig3();
}
