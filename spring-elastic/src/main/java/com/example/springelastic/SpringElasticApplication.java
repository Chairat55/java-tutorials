package com.example.springelastic;

import org.modelmapper.Condition;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MappingContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringElasticApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringElasticApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		final Condition<?, ?> alwaysTrue = new Condition<Object, Object>() {
			public boolean applies(MappingContext<Object, Object> context) {
				return true;
			}
		};
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		return modelMapper;
	}

}
