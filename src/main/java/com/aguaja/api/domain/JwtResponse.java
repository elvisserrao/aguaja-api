package com.aguaja.api.domain;

import java.io.Serializable;

public class JwtResponse implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;
	private final String jwttoken;
	private final Long id;
	private final String username;
	private final String email;
	private final Boolean admin;
	private final String role;


	public JwtResponse(String jwttoken, Long id, String username, String email, Boolean admin, String role) {
		this.jwttoken = jwttoken;
		this.id = id;
		this.username = username;
		this.email = email;
		this.admin = admin;
		this.role = role;
	}

	public String getToken() {
		return this.jwttoken;
	}
	public Long getId() {
		return this.id;
	}
	public String getUsername() {
		return this.username;
	}
	public String getEmail() {
		return this.email;
	}
	public Boolean getAdmin() {
		return this.admin;
	}
	public String getRole() {
		return this.role;
	}
}