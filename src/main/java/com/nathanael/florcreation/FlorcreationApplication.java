package com.nathanael.florcreation;

import com.nathanael.florcreation.errors.EntityExceptions;
import com.nathanael.florcreation.errors.InvalidArgumentException;
import graphql.GraphqlErrorBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.graphql.execution.DataFetcherExceptionResolver;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.graphql.execution.ErrorType;

import javax.validation.ConstraintViolationException;

@SpringBootApplication
public class FlorcreationApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlorcreationApplication.class, args);
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
