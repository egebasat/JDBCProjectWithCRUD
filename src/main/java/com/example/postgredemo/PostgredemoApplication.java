package com.example.postgredemo;

import lombok.extern.java.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@SpringBootApplication
@Log
public class PostgredemoApplication {


	public static void main(String[] args) {
		SpringApplication.run(PostgredemoApplication.class, args);
	}


}
