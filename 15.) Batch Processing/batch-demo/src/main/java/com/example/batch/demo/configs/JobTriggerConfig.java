package com.example.batch.demo.configs;

import org.springframework.batch.core.JobExecution;

public interface JobTriggerConfig {
    JobExecution triggerJob();
}
