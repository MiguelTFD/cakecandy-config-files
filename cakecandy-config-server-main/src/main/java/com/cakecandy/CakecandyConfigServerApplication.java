package com.cakecandy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
@EnableConfigServer
@SpringBootApplication
public class CakecandyConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CakecandyConfigServerApplication.class, args);
	}

}
