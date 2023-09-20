package com.prsystemjava.spring.request;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface RequestsRepository extends CrudRepository<Request, Integer>{
	//Optional<Request> setStatusAsReview(String review);
}
