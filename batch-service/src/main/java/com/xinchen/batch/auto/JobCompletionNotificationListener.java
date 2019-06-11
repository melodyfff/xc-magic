package com.xinchen.batch.auto;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * @author xinchen
 * @version 1.0
 * @date 11/06/2019 13:07
 */
@Component
@Slf4j
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

    private final JdbcTemplate jdbcTemplate;

    public JobCompletionNotificationListener(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        // 任务状态完成
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("!!! JOB FINISHED, Time to verify the results.");


            jdbcTemplate.query("SELECT first_name,last_name FROM people",
                    (rs, row) -> new Person(rs.getString(1), rs.getString(2)))
                    .forEach(person -> log.info("Found <{}> in the database.",person));
        }
    }
}
