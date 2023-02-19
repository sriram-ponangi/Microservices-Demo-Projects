package com.example.batch.demo.taskletConditionalJobflow.config.trigger;

import com.example.batch.demo.configs.JobConfig;
import com.example.batch.demo.configs.JobTriggerConfig;
import com.example.batch.demo.taskletConditionalJobflow.config.job.JobConditionalConfigImpl;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;


@Configuration("JobConditionalTriggerConfigImpl")
public class JobConditionalTriggerConfigImpl implements JobTriggerConfig {

    private final JobLauncher jobLauncher;
    private final JobConfig jobConfig;

    public JobConditionalTriggerConfigImpl(JobLauncher jobLauncher,
                                           @Qualifier("JobConditionalConfigImpl") JobConfig jobConfig) {
        this.jobLauncher = jobLauncher;
        this.jobConfig = jobConfig;
    }

    @Override
    public JobExecution triggerJob() {
        JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
        jobParametersBuilder.addLong("currentTimeMillis", System.currentTimeMillis());
        jobParametersBuilder.addString("Name", "Sriram Ponangi");

        try {
            JobExecution jobExecution = jobLauncher.run(jobConfig.mySimpleJob(),
                    jobParametersBuilder.toJobParameters());
            return jobExecution;
        } catch (JobExecutionAlreadyRunningException | JobRestartException |
                JobInstanceAlreadyCompleteException | JobParametersInvalidException e) {
            e.printStackTrace();
        }

        return null;
    }
}
