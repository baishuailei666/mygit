package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Administrator
 */
@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		// @SpringBootApplication相当于@Configuration，@EnableAutoConfiguration，@ComponentScan三个注解。
		// 用于自动完成Spring的配置和Bean的构建
		SpringApplication.run(DemoApplication.class, args);
	}
}
