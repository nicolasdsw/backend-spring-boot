package com.example.demo;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.controller.IndexController;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfig {

	private static final String GROUP = "backend-api";

	@Bean
	public GroupedOpenApi groupOpenApi() {
		String packagesToscan[] = { IndexController.class.getPackageName() };
		return GroupedOpenApi.builder().group(GROUP).pathsToMatch("/**").packagesToScan(packagesToscan).build();
	}

	@Bean
	public OpenAPI springOpenApi() {
		return new OpenAPI().info(apiInfo());
	}

	private Info apiInfo() {
		return new Info().title("Backend Spring - DEMO API").description("DEMO API").version("v1")
				.license(new License().name("Apache 2.0").url("http://springdoc.org"))
				.contact(new Contact().name("Nícolas Wojcichoski").url("https://github.com/nicolasdsw")
						.email("nicolas.dsw@gmail.com"));
	}
}
