package org.dellmdq.blogdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication
public class BlogdemoApplication {

	public static void main(String[] args) {

		//System.out.println(TimeZone.getDefault());
		SpringApplication.run(BlogdemoApplication.class, args);
	}

}
