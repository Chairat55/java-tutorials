package com.example.springmodelmapper;

import com.example.springmodelmapper.dtos.User;
import com.example.springmodelmapper.dtos.UserDetail;
import org.modelmapper.Condition;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MappingContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringModelMapperApplication {

	public static void main(String[] args) {
		User user = new User(1, "Chairat", 28);
		UserDetail userDetail = new UserDetail();

		modelMapper().map(user, userDetail);
		System.out.println("UserDetail id: " + userDetail.getId());
		System.out.println("UserDetail name: " + userDetail.getName());
		System.out.println("UserDetail age: " + userDetail.getAge());

		SpringApplication.run(SpringModelMapperApplication.class, args);
	}

	@Bean
	public static ModelMapper modelMapper() {
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
