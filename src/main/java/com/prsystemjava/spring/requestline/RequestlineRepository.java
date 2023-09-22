package com.prsystemjava.spring.requestline;



import org.springframework.data.repository.CrudRepository;

public interface RequestlineRepository extends CrudRepository<RequestLine, Integer>{

	Iterable<RequestLine> findAllByRequestId(int requestId);//added method to be used in the controller

	
	
	

	
}
