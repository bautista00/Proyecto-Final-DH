package com.example.backendpi;

import com.example.backendpi.domain.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;


@SpringBootApplication
public class BackendPiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendPiApplication.class, args);
	}


}
