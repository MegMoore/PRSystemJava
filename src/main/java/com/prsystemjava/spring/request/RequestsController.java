package com.prsystemjava.spring.request;

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

import com.prsystemjava.spring.user.User;

@CrossOrigin
@RestController
@RequestMapping("/api/requests")
public class RequestsController {
	
	@Autowired
	private RequestsRepository reqRepo;
	
	//Get All
	@GetMapping
	public ResponseEntity<Iterable<Request>> getRequest(){
		Iterable<Request> reqs= reqRepo.findAll();
		return new ResponseEntity<Iterable<Request>>(reqs, HttpStatus.OK);
	}
	
	//Get By Review
	@GetMapping("review/{userId}")
	public ResponseEntity<Iterable<Request>> getReview(@PathVariable int userId){
		Iterable<Request> reqs = reqRepo.findByStatusAndUserIdNot("REVIEW", userId);
		
		return new ResponseEntity<Iterable<Request>>(reqs, HttpStatus.OK);
	}
	
		
	
	
	//Get By Id
	@GetMapping("{id}")//get by id
	public ResponseEntity<Request> getRequest(@PathVariable int id){
		if(id <= 0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
	}
		Optional<Request> req = reqRepo.findById(id);
		if(req.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Request>(req.get(), HttpStatus.OK);
	}
	
	
	
	//Post
		@PostMapping
		public ResponseEntity<Request> postRequest(@RequestBody Request req){
			if(req.getId() != 0) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			reqRepo.save(req);
			return new ResponseEntity<Request>(req, HttpStatus.CREATED);
		}
		
		//PutReview
		
		@PutMapping("review/{id}")
		
			public ResponseEntity<?> putReview(@RequestBody Request req, @PathVariable int id) {
		        
		        	
		        		if(req.getTotal() < 50) {
		        		req.setStatus("REVIEW");
		        		}
		        		
		        		if(req.getTotal() >= 50) {
			        	req.setStatus("APPROVED");
		        		}
		        return putRequest(id, req);		
		}
				       
		//PutApproved
		@PutMapping("approved/{id}")
		public ResponseEntity<?> putApproved(@RequestBody Request req, @PathVariable int id) {
	        
			req.setStatus("APPROVED");
        		
        return putRequest(id, req);		
}
				
		//PutRejected
				@PutMapping("rejected/{id}")
				public ResponseEntity<?> putRejected(@RequestBody Request req, @PathVariable int id) {
			        
					req.setStatus("REJECTED");
		        		
		        return putRequest(id, req);		
		}
		
		
		//Put
		@PutMapping("{id}")
		public ResponseEntity<?> putRequest(@PathVariable int id, @RequestBody Request req) {
			if(req.getId() != id) {
				return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
			}
			reqRepo.save(req);
			return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
		}
		
		//Delete
		@DeleteMapping("{id}")
		public ResponseEntity<?> deleteRequest(@PathVariable int id) {
			if(id <= 0) {
				return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
			}
			reqRepo.deleteById(id);
			return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
		}
}
