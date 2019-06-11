package com.xinchen.batch.trigger;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

/**
 * @author xinchen
 * @version 1.0
 * @date 11/06/2019 14:45
 */
@Configuration
@EnableBatchProcessing
public class TriggerBatchConfig {


    @Bean
    @StepScope
    public FlatFileItemReader<Person> reader(@Value("#{jobParameters['input.file.name']}") String pathToFile) throws Exception {
        return new FlatFileItemReaderBuilder<Person>()
                .name("TriggerPersonItemReader")
                .resource(new ClassPathResource(pathToFile))
                .delimited()
                .names(new String[]{"firstName", "lastName"})
                .fieldSetMapper(new BeanWrapperFieldSetMapper<Person>() {{
                    setTargetType(Person.class);
                }})
                .build();
    }


//    @Bean
//    public ItemProcessor<Person,Person> processor{
//
//    }

}
