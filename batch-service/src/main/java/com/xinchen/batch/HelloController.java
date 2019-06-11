package com.xinchen.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xinchen
 * @version 1.0
 * @date 11/06/2019 16:07
 */
@RestController
public class HelloController {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    Job importJob;

    public JobParameters jobParameters;

    @RequestMapping("/load")
    public String load() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        jobParameters = new JobParametersBuilder().toJobParameters();
        // 触发批量处理
        jobLauncher.run(importJob,jobParameters);
        return "ok";
    }

}
