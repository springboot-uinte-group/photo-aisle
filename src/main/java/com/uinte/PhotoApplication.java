package com.uinte;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@MapperScan("com.uinte.mappers")
@ServletComponentScan
@SpringBootApplication
@EnableTransactionManagement
public class PhotoApplication {

	public static void main(String[] args) {
		for (String params : args) {
			System.out.println(params);
		}
		SpringApplication.run(PhotoApplication.class, args);
	}
}
