package com.example.demo;

import net.sf.jasperreports.engine.JRException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
public class DemoApplication {

	public static void main(String[] args) throws JRException {
		SpringApplication.run(DemoApplication.class, args);
	}

}
