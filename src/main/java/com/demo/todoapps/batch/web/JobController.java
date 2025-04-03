package com.demo.todoapps.batch.web;

import com.demo.todoapps.batch.config.BatchConfig;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/batch")
public class JobController {
    private final JobLauncher jobLauncher;
    private final Job helloworldJob;

    @Autowired
    public JobController(JobLauncher jobLauncher, Job helloworldJob) {
        this.jobLauncher = jobLauncher;
        this.helloworldJob = helloworldJob;
    }

    @GetMapping("/run-job")
    public String runJob() throws Exception{
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("time",System.currentTimeMillis())
                .toJobParameters();

                jobLauncher.run(helloworldJob, jobParameters);
        return "job 실행!";
    }
}
