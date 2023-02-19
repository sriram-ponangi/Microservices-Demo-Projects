package com.example.batch.demo.configs;

import org.springframework.batch.core.Step;

public interface StepConfig {
    Step myTaskletStep();
}
