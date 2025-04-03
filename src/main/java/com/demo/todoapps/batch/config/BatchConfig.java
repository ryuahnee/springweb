package com.demo.todoapps.batch.config;

import com.demo.todoapps.batch.core.Person;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.config.Task;
import org.springframework.transaction.PlatformTransactionManager;


@Configuration
public class BatchConfig {

    @Bean
    public Job helloworldJob(JobRepository jobRepository, Step helloworldStep){
        return new JobBuilder("helloWorldJob",jobRepository)
                .start(helloworldStep).build();
    }

    @Bean
    public Step helloworldStep(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager){
        return new StepBuilder("helloWorldStep",jobRepository)
                .tasklet(helloworldTask(), platformTransactionManager)
                .build();

    }

    @Bean
    public Tasklet helloworldTask(){
        return (contribution, chunkContext) -> {
            System.out.println("Hello, Spring Batch!");
            return RepeatStatus.FINISHED;
        };
    }


}
