package com.prsystemjava.spring.requestline;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.prsystemjava.spring.product.Product;
import com.prsystemjava.spring.request.Request;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="requestlines")
public class RequestLine {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id = 0;
	
	@JsonManagedReference
	@ManyToOne(optional=false)
	private Request request;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="productId")
	private Product product;
	
	@Column(columnDefinition="integer not null")
	private int quantity = 1;

	//getters and setters
	public int getId() {
		return id;
	}

	@SuppressWarnings("unused")
	private void setId(int id) {
		this.id = id;
	}

	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
	
}
