package ravi.labs.springbootreactaws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class SpringBootReactAwsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootReactAwsApplication.class, args);
	}

	@Bean
	public Docket docket()
	{
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage(getClass().getPackage().getName()))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(generateApiInfo());
	}


	private ApiInfo generateApiInfo()
	{
		return new ApiInfo("SpringBoot + React + AWS Tutorial", "SpringBoot + React + AWS Tutorial", "Version 1.0",
				"urn:tos", "raviprajna.prajna@gmail.com", "Apache 2.0", "https://raviprajna.gitlab.io");
	}
}
