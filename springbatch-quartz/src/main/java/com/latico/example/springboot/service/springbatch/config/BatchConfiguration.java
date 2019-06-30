package com.latico.example.springboot.service.springbatch.config;

import com.latico.example.springboot.service.springbatch.model.User;
import com.latico.example.springboot.service.springbatch.processor.MyProcessor;
import com.latico.example.springboot.service.springbatch.reader.MyReader;
import com.latico.example.springboot.service.springbatch.writer.MyWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
//@Import({QuartzConfiguration.class})
public class BatchConfiguration {
	
	@Autowired
	public JobBuilderFactory jobBuilderFactory;
	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	
	
	/*创建job*/
	@Bean
	public Job jobMethod(){
		return jobBuilderFactory.get("lzjJob")
				.start(stepMethod())
				.build();
	}
	
	/*创建step*/
	@Bean
	public Step stepMethod(){
		return stepBuilderFactory.get("myStep")
				.<User, User>chunk(3)
				.reader(new MyReader())
				.processor(new MyProcessor())
				.writer(new MyWriter())
				.allowStartIfComplete(true)
				.build();
	}
	

}
