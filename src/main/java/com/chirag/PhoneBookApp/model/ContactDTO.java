package com.chirag.PhoneBookApp.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class ContactDTO {

	@NotEmpty(message = "Name is missing")
	private String name;

	@NotEmpty(message = "Number is missing")
	@Size(min = 10, max = 10, message = "Phone No. must be of 10 digits")
	private String phone;

	@NotEmpty(message = "Email is missing")
	private String email;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public ContactDTO() {

	}

	public ContactDTO(@NotEmpty(message = "Please provide a name for this contact") String name,
			@NotEmpty(message = "Please provide a phone number for this contact") String phone,
			@NotEmpty(message = "Please provide an email address for this contact") String email) {
		super();
		this.name = name;
		this.phone = phone;
		this.email = email;
	}

}
