package com.toom.event_system;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Toom
 */
@SpringBootApplication
@MapperScan("com.toom.event_system.Mapper")
public class EventSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventSystemApplication.class, args);
	}

}
