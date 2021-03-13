package com.partycipate.Partycipate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;


@SpringBootApplication
public class PartycipateApplication{

	private static final Logger log = LoggerFactory.getLogger(PartycipateApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(PartycipateApplication.class, args);
	}



}
