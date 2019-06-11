package com.xinchen.batch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring batch主要有以下部分组成：
 *
 * JobRepository  　　   用来注册job的容器
 * JobLauncher           用来启动Job的接口
 * Job                   实际执行的任务，包含一个或多个Step
 * Step                  step包含ItemReader、ItemProcessor和ItemWriter
 * ItemReader            用来读取数据的接口
 * ItemProcessor         用来处理数据的接口
 * ItemWriter            用来输出数据的接口
 *
 *
 * reference:
 *
 * https://spring.io/guides/gs/batch-processing
 * https://www.cnblogs.com/kevin443/p/6753703.html
 *
 *
 * @author xinchen
 * @version 1.0
 * @date 11/06/2019 11:46
 */
@SpringBootApplication
public class BatchServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BatchServiceApplication.class, args);
    }

}
