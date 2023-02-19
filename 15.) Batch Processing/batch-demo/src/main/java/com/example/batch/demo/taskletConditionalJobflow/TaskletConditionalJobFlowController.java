package com.example.batch.demo.taskletConditionalJobflow;

import com.example.batch.demo.configs.JobTriggerConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskletConditionalJobFlowController {

    private final JobTriggerConfig jobTriggerConfig;

    public TaskletConditionalJobFlowController(
            @Qualifier("JobConditionalTriggerConfigImpl") JobTriggerConfig jobTriggerConfig) {
        this.jobTriggerConfig = jobTriggerConfig;
    }

    @GetMapping("/trigger/taskletConditionalJobFlow")
    private String stringJob() {
        return jobTriggerConfig.triggerJob().getExitStatus().getExitCode();
    }
}
