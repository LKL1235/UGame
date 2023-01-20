package com.myApp.gamestore;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("com.myApp.gamestore.mapper")
public class GameStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(GameStoreApplication.class, args);
	}
}
