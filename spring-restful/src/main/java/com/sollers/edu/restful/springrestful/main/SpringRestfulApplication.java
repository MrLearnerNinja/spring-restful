package com.sollers.edu.restful.springrestful.main;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@ComponentScan("com.sollers.*")
@EntityScan( basePackages = {"com.sollers.*"})
@EnableJpaRepositories(basePackages = {"com.sollers.*"})
@SpringBootApplication
public class SpringRestfulApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringRestfulApplication.class, args);
		Set<Integer> mySet = new HashSet<>();
	
	}
	
	
	@Bean
	public LocaleResolver localeResolver() {
		AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
		localeResolver.setDefaultLocale(Locale.US);
		return localeResolver;
	}
	
	
}
