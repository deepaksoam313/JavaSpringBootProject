package com.example.springbootrestfulwebservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

@SpringBootApplication //(exclude = {DataSourceAutoConfiguration.class})
//@ComponentScan("com.example.springbootrestfulwebservices.repository.UserRepository")
@ComponentScan( "com.example.springbootrestfulwebservices.controller.UserController")
@EnableJpaRepositories(basePackages = "com.example.springbootrestfulwebservices.repository.UserRepository")
@EntityScan(basePackages = "com.example.springbootrestfulwebservices.entity.User")
public class SpringbootRestfulWebservicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootRestfulWebservicesApplication.class, args);

//		@Bean(name="entityManagerFactory")
//		public LocalSessionFactoryBean sessionFactory() {
//		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
//
//		return sessionFactory;
//	}
	}

}
