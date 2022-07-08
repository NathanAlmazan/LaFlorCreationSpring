package com.nathanael.florcreation;

import com.nathanael.florcreation.errors.EntityExceptions;
import com.nathanael.florcreation.errors.InvalidArgumentException;
import graphql.GraphqlErrorBuilder;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.graphql.execution.DataFetcherExceptionResolver;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.ConstraintViolationException;

@SpringBootApplication
public class FlorcreationApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlorcreationApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigure() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(@NotNull CorsRegistry registry) {
				registry.addMapping("/graphql").allowedOrigins("http://localhost:3000")
						.allowedHeaders("Content-Type", "Accept")
						.allowedMethods("POST", "GET", "DELETE");
			}
		};
	}

	@Bean
	public DataFetcherExceptionResolver exceptionResolver() {
		return DataFetcherExceptionResolverAdapter.from((ex, env) -> {
			if (ex instanceof ConstraintViolationException) return GraphqlErrorBuilder.newError(env)
						.message(ex.getMessage()).errorType(ErrorType.BAD_REQUEST).build();
			else if (ex instanceof EntityExceptions) return GraphqlErrorBuilder.newError(env)
					.message(ex.getMessage()).errorType(ErrorType.BAD_REQUEST).build();
			else if (ex instanceof InvalidArgumentException) return GraphqlErrorBuilder.newError(env)
					.message(ex.getMessage()).errorType(ErrorType.BAD_REQUEST).build();
			else return null;
		});
	}

}
