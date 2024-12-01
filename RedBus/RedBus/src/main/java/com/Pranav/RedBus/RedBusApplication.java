package com.Pranav.RedBus;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@OpenAPIDefinition(
		info = @Info(
				title = "ONLINE BUS RESERVATION APPLICATION - GUVI PROJECT ",
				description = "Spring boot API doc to handle bus,user operations",
				version = "1.0",
				contact = @Contact(
						name = "PRANAV VISHAL",
						email = "pranav13vishal@gmail.com"
				)
		)
)
@SpringBootApplication
public class 	RedBusApplication {

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(RedBusApplication.class, args);
	}

}
