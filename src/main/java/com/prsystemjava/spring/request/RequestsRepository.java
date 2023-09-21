package com.prsystemjava.spring.request;

import org.springframework.data.repository.CrudRepository;


public interface RequestsRepository extends CrudRepository<Request, Integer>{

	Iterable<Request> findByStatusAndUserIdNot(String string, int userId);
	 	
	

	 
	 
	 

	
}
