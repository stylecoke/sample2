package com.board.config;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;



// @Configuration : Bean을 등록할때 싱글톤(singleton)이 되도록 보장해준다,  스프링 컨테이너에서 Bean을 관리할 수 있게 된다.
// @EnableWebMvc : HandlerMapping ,HandlerAdapter 등등 여러가지 Bean을 자동으로 추가해준다.
// WebMvcConfigurer : @EnableWebMvc가 자동적으로 세팅해주는 설정에 개발자가 원하는 설정을 추가할 수 있게 된다. ( pom.xml에서 spring version을 바꿔줄 것) 

@Configuration
@ComponentScan(
		basePackages = {"com.board"},
		excludeFilters = @Filter(
                type = FilterType.ANNOTATION,
                classes = {Service.class}
        )
)		
@EnableWebMvc
@EnableAspectJAutoProxy
public class Webconfig implements WebMvcConfigurer{
	
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		// DefaultServletHttpRequestHandler와  SimpleUrlHandlerMapping 2개의 bean 객체를 추가한다.
		// 별도 설정이 없는 모든 요청 경로를 DefaultServlet이 처리한다.
		configurer.enable();
	}
	
	// ViewResolver
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
	    registry.jsp("/WEB-INF/views/",".jsp");
	}
	
	// MessageSource
	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
	    messageSource.setBasename("message/message");
	    messageSource.setDefaultEncoding("UTF-8");
	 
	    return messageSource;
	}
	
	// Locale
	@Bean 
	public SessionLocaleResolver localeResolver() {    
		SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
		sessionLocaleResolver.setDefaultLocale(new Locale("ko"));
		
		return sessionLocaleResolver;
	}

}
