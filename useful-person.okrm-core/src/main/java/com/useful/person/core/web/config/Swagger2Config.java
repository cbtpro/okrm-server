package com.useful.person.core.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.useful.person.core.properties.AppProperties;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 
 * @author peter
 *
 */
@Profile("dev")
@Configuration
@EnableSwagger2
public class Swagger2Config {

	@Value("${spring.application.name}")
	private String projectName;

	@Autowired
	private AppProperties appProperties;

	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
				.apis(RequestHandlerSelectors.basePackage("com.useful.person.core.web.controller"))
				.paths(PathSelectors.any()).build();
	}

	private ApiInfo apiInfo() {
		String projectHost = appProperties.getHost();
		String projectPort = String.valueOf(appProperties.getPort());
		String url = projectHost + ":" + projectPort;
		String projectMail = appProperties.getMail();
		return new ApiInfoBuilder().title(projectName).contact(new Contact(projectName, url, projectMail))
				.description(projectName + "API").version("0.2").build();
	}
}
