package pl.piomin.services.department.config;
import static com.google.common.collect.Lists.newArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.common.base.Predicates;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig{

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				//.host("localhost:8080")
				.groupName("Public - Mock")
				.select()
				.apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.boot")))
				.apis(RequestHandlerSelectors.basePackage("pl.piomin.services.department"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(apiInfo())
				.useDefaultResponseMessages(false)
				.globalResponseMessage(
						RequestMethod.GET,
						newArrayList(new ResponseMessageBuilder().code(500).message("").build()));
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Department API Management")
				.description("A view of list of all Referrence API")
				.version("0.1")
				.build();
	}
}