package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class CorsConfig {

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {

				registry.addMapping("/**")
						.allowedOrigins("http://localhost:8081","/**","https://fe-goline-production.up.railway.app/")  // Cho phép yêu cầu từ localhost:8081
						.allowedMethods("GET", "POST", "PUT", "DELETE","PATCH")  // Cho phép các phương thức HTTP
						.allowedHeaders("*")  // Cho phép tất cả các header
						.allowCredentials(true);  // Nếu cần thiết, cho phép gửi cookie hoặc thông tin xác thực

			}
		};
	}
	
}
