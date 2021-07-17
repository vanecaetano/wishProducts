package com.vanessa.store

import com.vanessa.store.config.CustomerConfiguration
import com.vanessa.store.config.ProductConfiguration
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.web.bind.annotation.ControllerAdvice

@EnableAutoConfiguration
@Configuration
@ComponentScan
@EntityScan
@ControllerAdvice
@Import([ProductConfiguration.class, CustomerConfiguration.class])
@EnableJpaRepositories
class Main {

	static void main(String[] args) {
		SpringApplication.run(Main, args)
	}

}
