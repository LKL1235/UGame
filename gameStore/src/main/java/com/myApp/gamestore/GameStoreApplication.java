package com.myApp.gamestore;

import com.myApp.gamestore.controller.WebSocketServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
@MapperScan("com.myApp.gamestore.mapper")
public class GameStoreApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext =SpringApplication.run(GameStoreApplication.class, args);
		WebSocketServer.setApplicationContext(applicationContext);
	}
}
