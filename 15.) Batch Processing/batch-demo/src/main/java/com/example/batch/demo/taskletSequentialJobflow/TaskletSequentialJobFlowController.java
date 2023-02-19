package com.example.batch.demo.taskletSequentialJobflow;

import com.example.batch.demo.configs.JobTriggerConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskletSequentialJobFlowController {

    private final JobTriggerConfig jobTriggerConfig;

    public TaskletSequentialJobFlowController(
            @Qualifier("JobSequentialTriggerConfigImpl") JobTriggerConfig jobTriggerConfig) {
        this.jobTriggerConfig = jobTriggerConfig;
    }

    @GetMapping("/trigger/taskletSequentialJobFlow")
    private String stringJob() {
        return jobTriggerConfig.triggerJob().getExitStatus().getExitCode();
    }
}
