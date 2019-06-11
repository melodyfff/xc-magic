package com.xinchen.batch.trigger;

import com.xinchen.batch.auto.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * 任务监听器
 *
 * @author xinchen
 * @version 1.0
 * @date 11/06/2019 15:44
 */
@Slf4j
public class PersonJobListener implements JobExecutionListener {

    private final JdbcTemplate jdbcTemplate;

    public PersonJobListener(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    long startTime;

    @Override
    public void beforeJob(JobExecution jobExecution) {
        startTime = System.currentTimeMillis();
        log.info("任务处理开始...");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("任务处理结束...");
            log.info("耗时: {} ms", System.currentTimeMillis() - startTime);

            jdbcTemplate.query("SELECT first_name,last_name FROM people",
                    (rs, row) -> new Person(rs.getString(1), rs.getString(2)))
                    .forEach(person -> log.info("Found <{}> in the database.",person));
        }
    }
}
