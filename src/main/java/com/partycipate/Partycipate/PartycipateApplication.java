package com.partycipate.Partycipate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@SpringBootApplication
@CrossOrigin(origins = "*")
public class PartycipateApplication{

	private static final Logger log = LoggerFactory.getLogger(PartycipateApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(PartycipateApplication.class, args);
	}

	/*@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/*").allowCredentials(true).allowedMethods("*").allowedOriginPatterns("*").allowedOrigins("http://localhost:1234/").allowedHeaders("*")
						.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
						.maxAge(3600L);
			}
		};
	}*/

}
