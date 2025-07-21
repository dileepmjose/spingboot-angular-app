package com.process.batch.scheduler;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JobScheduler {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job mongoToCsvJob;

    @Scheduled(cron = "0 0/1 * * * ?")
    public void runJob() {
        try {
            System.out.println(">>>> Scheduler triggered at: " + new Date());
            JobParameters params = new JobParametersBuilder()
                    .addDate("runTime", new Date()) // ensure uniqueness
                    .toJobParameters();

            JobExecution execution = jobLauncher.run(mongoToCsvJob, params);
            System.out.println("Batch job started at: " + execution.getStartTime() +
                    " with status: " + execution.getStatus());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}