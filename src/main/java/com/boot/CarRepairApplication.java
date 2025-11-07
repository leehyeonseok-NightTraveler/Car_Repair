package com.boot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.boot.dao")
public class CarRepairApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarRepairApplication.class, args);
	}

}
