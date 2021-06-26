package com.ecommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.ecommerce"})
public class WebMvcConfig implements WebMvcConfigurer {

	

	public void addResourceHandlers(ResourceHandlerRegistry registry) {
	
		registry
		.addResourceHandler("/admin/**") // Tên thay thế khi sử dụng trên trang web
		.addResourceLocations("classpath:static"); // Tên thư mục chứa file css|js|image
		
		registry
		.addResourceHandler("/**") // Tên thay thế khi sử dụng trên trang web
		.addResourceLocations("classpath:static"); // Tên thư mục chứa file css|js|image
	}
	

}
