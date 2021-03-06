package shift.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.google.common.collect.Lists;
import org.apache.log4j.Logger;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static springfox.documentation.builders.PathSelectors.regex;

@SpringBootApplication
@EntityScan("shift.management.entity")
@EnableSwagger2
public class ShiftManagementApplication {
	public static final Logger logger = Logger.getLogger(ShiftManagementApplication.class);
	@Bean
	public Docket swaggerSpringfoxDocket() {
		logger.debug("Starting Swagger");
		Contact contact = new Contact(
				"Matyas Albert-Nagy",
				"https://justrocket.de",
				"matyas@justrocket.de");

		List<VendorExtension> vext = new ArrayList<>();
		ApiInfo apiInfo = new ApiInfo(
				"Backend API",
				"This is the best stuff since sliced bread - API",
				"6.6.6",
				"https://justrocket.de",
				contact,
				"MIT",
				"https://justrocket.de",
				vext);

		Docket docket = new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo)
				.pathMapping("/")
				.apiInfo(ApiInfo.DEFAULT)
				.forCodeGeneration(true)
				.genericModelSubstitutes(ResponseEntity.class)
				.ignoredParameterTypes(Pageable.class)
				.ignoredParameterTypes(java.sql.Date.class)
				.directModelSubstitute(java.time.LocalDate.class, java.sql.Date.class)
				.directModelSubstitute(java.time.ZonedDateTime.class, Date.class)
				.directModelSubstitute(java.time.LocalDateTime.class, Date.class)
				.securityContexts(Lists.newArrayList(securityContext()))
				.securitySchemes(Lists.newArrayList(apiKey()))
				.useDefaultResponseMessages(false);

		docket = docket.select()
				.paths(regex(DEFAULT_INCLUDE_PATTERN))
				.build();

//		log.debug("Started Swagger in {} ms", watch.getTotalTimeMillis());
		return docket;
	}
	public static final String AUTHORIZATION_HEADER = "Authorization";
	public static final String DEFAULT_INCLUDE_PATTERN = "/api/.*";
	private ApiKey apiKey() {
		return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
	}

	private SecurityContext securityContext() {
		return SecurityContext.builder()
				.securityReferences(defaultAuth())
				.forPaths(regex(DEFAULT_INCLUDE_PATTERN))
				.build();
	}

	List<SecurityReference> defaultAuth() {
		AuthorizationScope authorizationScope
				= new AuthorizationScope("global", "accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		return Lists.newArrayList(
				new SecurityReference("JWT", authorizationScopes));
	}
	public static void main(String[] args) {
		SpringApplication.run(ShiftManagementApplication.class, args);
	}
}
