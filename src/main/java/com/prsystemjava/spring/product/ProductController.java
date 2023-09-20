package com.prsystemjava.spring.product;

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
@RequestMapping("/api/products")
public class ProductController {

	@Autowired
	private ProductRepository proRepo;
	
	//Get All
	@GetMapping
	public ResponseEntity<Iterable<Product>> getProduct(){
		Iterable<Product> prod= proRepo.findAll();
		return new ResponseEntity<Iterable<Product>>(prod, HttpStatus.OK);
	}
	//Get By Id
	@GetMapping("{id}")//get by id
	public ResponseEntity<Product> getProduct(@PathVariable int id){
		if(id <= 0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
	}
		Optional<Product> pro = proRepo.findById(id);
		if(pro.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Product>(pro.get(), HttpStatus.OK);
	}
	
	
	
	//Post
		@PostMapping
		public ResponseEntity<Product> postProduct(@RequestBody Product pro){
			if(pro.getId() != 0) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			proRepo.save(pro);
			return new ResponseEntity<Product>(pro, HttpStatus.CREATED);
		}
		
		//Put
		@PutMapping("{id}")
		public ResponseEntity putProduct(@PathVariable int id, @RequestBody Product pro) {
			if(pro.getId() != id) {
				return new ResponseEntity(HttpStatus.BAD_REQUEST);
			}
			proRepo.save(pro);
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		
		//Delete
		@DeleteMapping("{id}")
		public ResponseEntity deleteProduct(@PathVariable int id) {
			if(id <= 0) {
				return new ResponseEntity(HttpStatus.BAD_REQUEST);
			}
			proRepo.deleteById(id);
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
}
