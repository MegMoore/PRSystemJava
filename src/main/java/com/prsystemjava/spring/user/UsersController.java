package com.prsystemjava.spring.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api/users")
public class UsersController {
	
	@Autowired
	private UserRepository useRepo;
	
	//Get All
	@GetMapping
	public ResponseEntity<Iterable<User>> getUser(){
		Iterable<User> user= useRepo.findAll();
		return new ResponseEntity<Iterable<User>>(user, HttpStatus.OK);
	}
	//Get By Id
	@GetMapping("{id}")//get by id
	public ResponseEntity<User> getUser(@PathVariable int id){
		if(id <= 0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
	}
		Optional<User> use = useRepo.findById(id);
		if(use.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(use.get(), HttpStatus.OK);
	}
	
	//Get By username and password
	@GetMapping("{username}/{password}")//get customer by username and password
	public ResponseEntity<User> getUserByUsernameAndPassword(@PathVariable String username, @PathVariable String password){
		Optional<User> use = useRepo.findUserByUsernameAndPassword(username, password);
		if(use.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(use.get(), HttpStatus.OK);
	
	}
	
	//Post
		@PostMapping
		public ResponseEntity<User> postUser(@RequestBody User use){
			if(use.getId() != 0) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			useRepo.save(use);
			return new ResponseEntity<User>(use, HttpStatus.CREATED);
		}
		
		//Put
		@PutMapping("{id}")
		public ResponseEntity putUser(@PathVariable int id, @RequestBody User use) {
			if(use.getId() != id) {
				return new ResponseEntity(HttpStatus.BAD_REQUEST);
			}
			useRepo.save(use);
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		
		//Delete
		@DeleteMapping("{id}")
		public ResponseEntity deleteUser(@PathVariable int id) {
			if(id <= 0) {
				return new ResponseEntity(HttpStatus.BAD_REQUEST);
			}
			useRepo.deleteById(id);
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}

}
