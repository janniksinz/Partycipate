package com.partycipate.Partycipate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@SpringBootApplication
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
