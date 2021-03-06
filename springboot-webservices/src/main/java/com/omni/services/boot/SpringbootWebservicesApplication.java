package com.omni.services.boot;

import java.io.FileReader;
import java.io.IOException;

import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class SpringbootWebservicesApplication {

	//http://localhost:2222/actuator/
	//http://localhost:2222/swagger-ui.html
	
	public static void main(String[] args) {
		SpringApplication.run(SpringbootWebservicesApplication.class, args);
	}
	
	@Bean
	public Docket api() throws IOException, XmlPullParserException {
		MavenXpp3Reader reader = new MavenXpp3Reader();
		Model model = reader.read(new FileReader("pom.xml"));
		ApiInfoBuilder builder = new ApiInfoBuilder()
				.title("Web Service Api Documentation")
				.description("Documentation generated by Swagger")
				.version(model.getVersion())
				.contact(new Contact("R Guerra", "rguerra", "rguerra@mail.com"));
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.omni.services.boot.controller"))
				.paths(PathSelectors.any()).build()
				.apiInfo(builder.build());
	}
	
	@Bean
	UiConfiguration uiConfig() {
	    return new UiConfiguration("validatorUrl", "list", "alpha", "schema",
	            UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS, false, true, 60000L);
	}

}
