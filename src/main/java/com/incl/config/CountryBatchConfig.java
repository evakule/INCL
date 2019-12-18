package com.incl.config;

import com.incl.model.CountryEntity;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

@Configuration
@EnableBatchProcessing
public class CountryBatchConfig {
  private static final String BUILD = "Country-build";
  private static final String LOAD = "Country-load";
  private static final String READER = "CSV-Reader";
  private static final String DELIMITER = ",";
  private static final String FILE_SOURCE = "src/main/java/com/incl/prerequisites/countries.csv";
  private static final String[] FIELDS = {"ID", "TITLE", "PHONE_PREFIX"};
  
  @Bean
  public Job job(
      JobBuilderFactory jobBuilderFactory,
      StepBuilderFactory stepBuilderFactory,
      ItemReader<CountryEntity> itemReader,
      ItemProcessor<CountryEntity, CountryEntity> itemProcessor,
      ItemWriter<CountryEntity> itemWriter
  ) {
    
    Step step = stepBuilderFactory.get(BUILD)
        .<CountryEntity, CountryEntity>chunk(100)
        .reader(itemReader)
        .processor(itemProcessor)
        .writer(itemWriter)
        .build();
    
    
    return jobBuilderFactory.get(LOAD)
        .incrementer(new RunIdIncrementer())
        .start(step)
        .build();
  }
  
  @Bean
  public FlatFileItemReader<CountryEntity> itemReader() {
    
    FlatFileItemReader<CountryEntity> flatFileItemReader =
        new FlatFileItemReader<>();
    flatFileItemReader.setResource(
        new FileSystemResource(FILE_SOURCE));
    flatFileItemReader.setName(READER);
    flatFileItemReader.setLinesToSkip(1);
    flatFileItemReader.setLineMapper(lineMapper());
    return flatFileItemReader;
  }
  
  @Bean
  public LineMapper<CountryEntity> lineMapper() {
    DefaultLineMapper<CountryEntity> defaultLineMapper =
        new DefaultLineMapper<>();
    DelimitedLineTokenizer lineTokenizer =
        new DelimitedLineTokenizer();
    lineTokenizer.setDelimiter(DELIMITER);
    lineTokenizer.setStrict(false);
    lineTokenizer.setNames(FIELDS);
    
    BeanWrapperFieldSetMapper<CountryEntity> fieldSetMapper =
        new BeanWrapperFieldSetMapper<>();
    fieldSetMapper.setTargetType(CountryEntity.class);
    defaultLineMapper.setLineTokenizer(lineTokenizer);
    defaultLineMapper.setFieldSetMapper(fieldSetMapper);
    return defaultLineMapper;
  }
}
