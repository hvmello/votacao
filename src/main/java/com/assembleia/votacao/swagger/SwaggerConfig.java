package com.assembleia.votacao.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.function.Predicate;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select().
						  apis(RequestHandlerSelectors.basePackage("com.assembleia.votacao")).
						  paths(PathSelectors.any()).build().apiInfo(apiInfo());
	}

	private Predicate<RequestHandler> apis() {
		return (Predicate<RequestHandler>) RequestHandlerSelectors.basePackage("com.assembleia.votacao");
	}


	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Swagger API")
				.description("Documentação da API de acesso aos endpoints com Swagger").version("1.0")
				.build();
	}

}

