package com.batch.demo.config;

import com.batch.demo.model.Person;
import com.batch.demo.processor.PersonItemProcessor;
import com.batch.demo.reader.PersonItemReader;
import com.batch.demo.writer.PersonItemWriter;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.*;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.data.MongoItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Bean
    public Job personJob(JobRepository jobRepository, Step step) {
        return new JobBuilder("personJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .flow(step)
                .end()
                .build();
    }

    @Bean
    public Step step(JobRepository jobRepository, PlatformTransactionManager transactionManager,
                     MongoItemReader<Person> reader, PersonItemProcessor processor, FlatFileItemWriter<Person> writer) {
        return new StepBuilder("step", jobRepository)
                .<Person, Person>chunk(10, transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }


    @Bean
    public PersonItemReader personItemReader() {
        return new PersonItemReader();
    }

    @Bean
    public PersonItemProcessor personItemProcessor() {
        return new PersonItemProcessor();
    }

    @Bean
    public PersonItemWriter personItemWriter() {
        return new PersonItemWriter();
    }
}