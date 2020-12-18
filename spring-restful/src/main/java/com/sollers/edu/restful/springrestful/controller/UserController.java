package com.sollers.edu.restful.springrestful.controller;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import com.sollers.edu.restful.springrestful.exceptions.UserNotFoundException;
import com.sollers.edu.restful.springrestful.user.User;
import com.sollers.edu.restful.springrestful.user.UserDAOService;



import org.springframework.hateoas.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;



@RestController
public class UserController{
	
	@Autowired
	private UserDAOService userDAOService;
	
	
	
	/*@GetMapping("/users")
	public Set<User> retrieveAllUsers() {
		return userDAOService.findAll();
	}*/
	@GetMapping("/users")
	public Set<User> retrieveAllUsers() {
		return userDAOService.findAll();
	}
	
	@GetMapping("/users/{id}")
	public EntityModel<User> retrieveUser(@PathVariable int id) {
		
		User user = userDAOService.findOne(id);
		if(user == null)
			throw new UserNotFoundException("id-"+ id);
		
		EntityModel<User> resource = new EntityModel<User>(user);
		
		Link findLink = linkTo(methodOn(UserController.class).retrieveAllUsers()).withSelfRel();
		
		resource.add(findLink.withRel("all-users"));
		
		//HATEOAS
		
		return resource;
		
	}
	
	@PostMapping(path ="/users", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		
		User savedUser = userDAOService.save(user);
		
		java.net.URI location = ServletUriComponentsBuilder.fromCurrentRequest()
								   .path("/{id}")
								   .buildAndExpand(savedUser.getId())
								   .toUri();
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/users/{id}")
	public User deleteUser(@PathVariable int id) {
		User deletedUser = userDAOService.deleteById(id);
		return deletedUser;
		
	}
	
	
	
	
	
	
}


