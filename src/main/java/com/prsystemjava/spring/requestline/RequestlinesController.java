package com.prsystemjava.spring.requestline;

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
@RequestMapping("/api/requestlines")
public class RequestlinesController {

	@Autowired
	private RequestlineRepository reqlRepo;
	
	//Get All
	@GetMapping
	public ResponseEntity<Iterable<RequestLine>> getProduct(){
		Iterable<RequestLine> reqls= reqlRepo.findAll();
		return new ResponseEntity<Iterable<RequestLine>>(reqls, HttpStatus.OK);
	}
	//Get By Id
	@GetMapping("{id}")//get by id
	public ResponseEntity<RequestLine> getRequestLine(@PathVariable int id){
		if(id <= 0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
	}
		Optional<RequestLine> reql = reqlRepo.findById(id);
		if(reql.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<RequestLine>(reql.get(), HttpStatus.OK);
	}
	
	
	
	//Post
		@PostMapping
		public ResponseEntity<RequestLine> postRequestLine(@RequestBody RequestLine reql){
			if(reql.getId() != 0) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			reqlRepo.save(reql);
			return new ResponseEntity<RequestLine>(reql, HttpStatus.CREATED);
		}
		
		//Put
		@PutMapping("{id}")
		public ResponseEntity putRequestLine(@PathVariable int id, @RequestBody RequestLine reql) {
			if(reql.getId() != id) {
				return new ResponseEntity(HttpStatus.BAD_REQUEST);
			}
			reqlRepo.save(reql);
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		
		//Delete
		@DeleteMapping("{id}")
		public ResponseEntity deleteRequestLine(@PathVariable int id) {
			if(id <= 0) {
				return new ResponseEntity(HttpStatus.BAD_REQUEST);
			}
			reqlRepo.deleteById(id);
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
}


