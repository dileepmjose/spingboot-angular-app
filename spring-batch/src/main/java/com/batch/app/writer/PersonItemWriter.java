package com.batch.demo.writer;

import com.batch.demo.model.Person;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;

public class PersonItemWriter {
    @Bean
    public FlatFileItemWriter<Person> writer() {
        BeanWrapperFieldExtractor<Person> extractor = new BeanWrapperFieldExtractor<>();
        extractor.setNames(new String[]{"index", "name", "isActive", "registered", "age", "gender", "eyeColor", "favoriteFruit"});

        DelimitedLineAggregator<Person> aggregator = new DelimitedLineAggregator<>();
        aggregator.setDelimiter(",");
        aggregator.setFieldExtractor(extractor);

        return new FlatFileItemWriterBuilder<Person>()
                .name("personItemWriter")
                .resource(new FileSystemResource("output/persons.csv"))
                .lineAggregator(aggregator)
                .headerCallback(writer -> writer.write("Index,Name,Active,Registered,Age,Gender,EyeColor,FavoriteFruit"))
                .build();
    }
}
