package com.process.batch.config;

import com.process.batch.model.Person;
import com.process.batch.processor.PersonItemProcessor;
import com.process.batch.reader.MongoPersonItemReader;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.*;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.*;

@Configuration
public class BatchConfig {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Bean
    public Job job(JobLauncher jobLauncher, Step step) {
        return new JobBuilder("mongoToCsvJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(step)
                .build();
    }

    @Bean
    public Step step(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("step", jobRepository)
                .<Person, String>chunk(10, transactionManager)
                .reader(mongoReader())
                .processor(new PersonItemProcessor())
                .writer(csvWriter())
                .build();
    }

    @Bean
    public ItemReader<Person> mongoReader() {
        return new MongoPersonItemReader(mongoTemplate);
    }

    @Bean
    public FlatFileItemWriter<String> csvWriter() {
        return new FlatFileItemWriterBuilder<String>()
                .name("csvWriter")
                .resource(new FileSystemResource("output/persons.csv"))
                .lineAggregator(item -> item)
                .build();
    }
}
