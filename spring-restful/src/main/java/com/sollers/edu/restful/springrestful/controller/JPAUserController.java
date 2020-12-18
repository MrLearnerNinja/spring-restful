package com.sollers.edu.restful.springrestful.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Set;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sollers.edu.restful.springrestful.exceptions.UserNotFoundException;
import com.sollers.edu.restful.springrestful.user.JPAUser;
import com.sollers.edu.restful.springrestful.user.JPAUserRepository;
import com.sollers.edu.restful.springrestful.user.Post;
import com.sollers.edu.restful.springrestful.user.PostRepository;
import com.sollers.edu.restful.springrestful.user.User;
import com.sollers.edu.restful.springrestful.user.UserDAOService;

@RestController
public class JPAUserController {
	
	@Autowired
	private JPAUserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	
	
	/*@GetMapping("/users")
	public Set<User> retrieveAllUsers() {
		return userDAOService.findAll();
	}*/
	@GetMapping("/jpa/users")
	public List<JPAUser> retrieveAllUsers() {
		return (List<JPAUser>) userRepository.findAll();
	}
	
	@GetMapping("/jpa/posts")
	public List<Post> retrieveAllPosts() {
		return (List<Post>) postRepository.findAll();
	}
	
	@GetMapping("/jpa/users/{id}")
	public EntityModel<JPAUser> retrieveUser(@PathVariable int id) {
		Optional<JPAUser> user = userRepository.findById(id);

		if (!user.isPresent())
			throw new UserNotFoundException("id-" + id);

		// "all-users", SERVER_PATH + "/users"
		// retrieveAllUsers
		EntityModel<JPAUser> resource = new EntityModel<JPAUser>(user.get());
		
		Link findLink = linkTo(methodOn(JPAUserController.class).retrieveAllUsers()).withSelfRel();
		
		resource.add(findLink.withRel("all-users"));
		// HATEOAS

		return resource;
	}
	
	@DeleteMapping("/jpa/users/{id}")
	public void deleteUser(@PathVariable int id) {
		userRepository.deleteById(id);
	}

	//
	// input - details of user
	// output - CREATED & Return the created URI

	// HATEOAS

	@PostMapping("/jpa/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody JPAUser user) {
		JPAUser savedUser = userRepository.save(user);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();

		return ResponseEntity.created(location).build();

	}
	
	/*@GetMapping("/users/{id}")
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
	*/
	
	@GetMapping("/jpa/users/{id}/posts")
	public List<Post> retrieveAllUsers(@PathVariable int id) {
		Optional<JPAUser> userOptional = userRepository.findById(id);
		
		if(!userOptional.isPresent()) {
			throw new UserNotFoundException("id-" + id);
		}
		
		return userOptional.get().getPosts();
	}


	@PostMapping("/jpa/users/{id}/posts")
	public ResponseEntity<Object> createPost(@PathVariable int id, @RequestBody Post post) {
		
		Optional<JPAUser> userOptional = userRepository.findById(id);
		
		if(!userOptional.isPresent()) {
			throw new UserNotFoundException("id-" + id);
		}

		JPAUser user = userOptional.get();
		
		post.setUser(user);
		
		postRepository.save(post);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post.getId())
				.toUri();

		return ResponseEntity.created(location).build();

	}

	
	
	

}
