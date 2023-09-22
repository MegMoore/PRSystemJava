package com.prsystemjava.spring.requestline;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
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

import com.prsystemjava.spring.request.RequestsRepository;
import com.prsystemjava.spring.request.Request;

@CrossOrigin
@RestController
@RequestMapping("/api/requestlines")
public class RequestlinesController {

	@Autowired
	private RequestlineRepository reqlRepo;
	@Autowired
	private RequestsRepository reqRepo;
	
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
		public ResponseEntity<RequestLine> postRequestLine(@RequestBody RequestLine reql) throws Exception {
			if(reql.getId() != 0) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			reqlRepo.save(reql);
			recalculateRequestTotal(reql.getRequest().getId());
			return new ResponseEntity<RequestLine>(reql, HttpStatus.CREATED);
		}
		
		//Put
		@PutMapping("{id}")
		public ResponseEntity putRequestLine(@PathVariable int id, @RequestBody RequestLine reql) throws Exception {
			if(reql.getId() != id) {
				return new ResponseEntity(HttpStatus.BAD_REQUEST);
			}
			reqlRepo.save(reql);
			recalculateRequestTotal(reql.getRequest().getId());
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		
		//Delete
		@DeleteMapping("{id}")
		public ResponseEntity deleteRequestLine(@PathVariable int id) throws Exception/*had to add because of the 
		recalculateRequestTotal() method that has exceptions in it*/ {
			if(id <= 0) {
				return new ResponseEntity(HttpStatus.BAD_REQUEST);
			}
			Optional<RequestLine> reql = reqlRepo.findById(id);//had to add so we could find the id
			reqlRepo.deleteById(id);
			recalculateRequestTotal(reql.get().getRequest().getId());//called private method to update automatically
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		
		//Automatically updates when added to other method's code
		private void recalculateRequestTotal(int requestId) throws Exception /* must add after putting in exception*/ {
			
			Optional<Request> req = reqRepo.findById(requestId);//optional will return non or one
			if(req.isEmpty()) {									//had to call Request class not Product class
				throw new Exception("Did not find");
			}
			Iterable<RequestLine> reqls = reqlRepo.findAllByRequestId(requestId);//iterable is a collection
			double total = 0;		//added variable to calculate 
			for(RequestLine rl: reqls) { //called RequestLive class(renamed rl) and reql from previous lines
				total =+ rl.getQuantity() * rl.getProduct().getPrice(); //attained by calling methods from Request class
			}
			req.get().setTotal(total); //setting new balance/total
			reqRepo.save(req.get());//saving updates
			
			return;
		}
		
		
		
		
		
		
		
		
		
		
		
}


