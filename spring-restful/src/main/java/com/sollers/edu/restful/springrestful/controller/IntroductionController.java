package com.sollers.edu.restful.springrestful.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.converter.json.MappingJacksonValue;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.sollers.edu.restful.springrestful.beans.HelloWorldBean;
import com.sollers.edu.restful.springrestful.user.User;

//Controller
@RestController
public class IntroductionController {
	
	@Autowired
    MessageSource messageSource;
	
	
	
	//@RequestMapping(method=RequestMethod.GET,path="/firstController")
	@GetMapping(path="/firstController")
	public HelloWorldBean enterController() {
		
		return new HelloWorldBean("Hello World");
	}
	
	@GetMapping(path="/firstControllerAnother")
	public String enterControllerAnother() {
		
		return "First Controller Another";
	}
	
	@GetMapping(path="/firstControllerPath/{name}")
	public HelloWorldBean enterControllerPath(@PathVariable String name ) {
		
		return new HelloWorldBean(name);
	}
	
	@GetMapping(path = "/locale")
	public String helloWorldInternationalized(@RequestHeader(name="Accept-Language", required=false) Locale locale)  
	{  
	return messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale());  
	}  
	
	@GetMapping(path="introusers",headers="X-version=v1")
	public User retrieveUsers()
	{
		User user = new User(9,"Dynamic",new Date());
		return user;  
		
	}
	
	@GetMapping(path="introusers",headers="X-version=v2")
	public User retrieveUsersV2()
	{
		User user = new User(9,"Dynamic",new Date(),"Enginneer");
		return user;  
		
	}
	
	
	

}
