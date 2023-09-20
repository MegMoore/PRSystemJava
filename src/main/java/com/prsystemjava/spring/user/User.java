package com.prsystemjava.spring.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name="users", uniqueConstraints=@UniqueConstraint(name="UIDX_Username", columnNames= {"username"}))
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id = 0;
	@Column(length=30, nullable=false)
	private String username = "";
	@Column(length=30, nullable=false)
	private String password = "";
	@Column(length=30, nullable=false)
	private String firstName = "";
	@Column(length=30, nullable=false)
	private String lastName = "";
	@Column(length=12, nullable=true)
	private String phone = "";
	@Column(length=255, nullable=true)
	private String email = "";
	@Column(columnDefinition = "boolean", nullable=false)
	private boolean isReviewer = false;
	@Column(columnDefinition = "boolean", nullable=false)
	private boolean inAdmin = false;
	
	//getters and setters
	public int getId() {
		return id;
	}
	private void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	private String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isReviewer() {
		return isReviewer;
	}
	private void setReviewer(boolean isReviewer) {
		this.isReviewer = isReviewer;
	}
	public boolean isInAdmin() {
		return inAdmin;
	}
	private void setInAdmin(boolean inAdmin) {
		this.inAdmin = inAdmin;
	}
	
	
	
}
