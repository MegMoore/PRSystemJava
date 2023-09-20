package com.prsystemjava.spring.vendor;

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
import com.prsystemjava.spring.user.UserRepository;

@CrossOrigin
@RestController
@RequestMapping("/api/vendors")
public class VendorsController {

	@Autowired
	private VendorRepository venRepo;
	
	//Get All
	@GetMapping
	public ResponseEntity<Iterable<Vendor>> getVendor(){
		Iterable<Vendor> vend= venRepo.findAll();
		return new ResponseEntity<Iterable<Vendor>>(vend, HttpStatus.OK);
	}
	//Get By Id
	@GetMapping("{id}")//get by id
	public ResponseEntity<Vendor> getVendor(@PathVariable int id){
		if(id <= 0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
	}
		Optional<Vendor> ven = venRepo.findById(id);
		if(ven.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Vendor>(ven.get(), HttpStatus.OK);
	}
	
	//Get By Code
	@GetMapping("code/{code}")//get vendor by code
	public ResponseEntity<Vendor> getVendorByCode(@PathVariable String code){
		Optional<Vendor> ven = venRepo.findVendorByCode(code);
		if(ven.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Vendor>(ven.get(), HttpStatus.OK);
	
	}
	
	//Post
		@PostMapping
		public ResponseEntity<Vendor> postVendor(@RequestBody Vendor ven){
			if(ven.getId() != 0) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			venRepo.save(ven);
			return new ResponseEntity<Vendor>(ven, HttpStatus.CREATED);
		}
		
		//Put
		@PutMapping("{id}")
		public ResponseEntity putVendor(@PathVariable int id, @RequestBody Vendor ven) {
			if(ven.getId() != id) {
				return new ResponseEntity(HttpStatus.BAD_REQUEST);
			}
			venRepo.save(ven);
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		
		//Delete
		@DeleteMapping("{id}")
		public ResponseEntity deleteVendor(@PathVariable int id) {
			if(id <= 0) {
				return new ResponseEntity(HttpStatus.BAD_REQUEST);
			}
			venRepo.deleteById(id);
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
}
