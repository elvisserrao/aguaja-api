package com.aguaja.api.domain;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.aguaja.api.domain.interfaces.HasPhone;

@Entity
@Table(name = "tb_seller")
public class Seller implements Serializable, HasPhone{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private Integer gender;
	private Instant birthDate;
	private String login;
	private String password;
	private String email;
	private Integer admin;

	@OneToOne
	@JoinColumn(name="phone_id")
	private Phone phone;

	@OneToOne
	@JoinColumn(name="address_id")
	private Address address;

	@OneToMany(mappedBy = "seller")
	private List<Order> orders = new ArrayList<>();

	public Seller() {
		super();
	}

	public Seller(Integer id, String name, Integer gender, Instant birthDate, String login, String password,
			String email, Integer admin) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.birthDate = birthDate;
		this.login = login;
		this.password = password;
		this.email = email;
		this.admin = admin;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public Instant getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Instant birthDate) {
		this.birthDate = birthDate;
	}

	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getAdmin() {
		return admin;
	}
	public void setAdmin(Integer admin) {
		this.admin = admin;
	}

	public Phone getPhone() {
		return phone;
	}
	public void setPhone(Phone phone) {
		this.phone = phone;
	}

	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Order> getOrders() {
		return orders;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Seller other = (Seller) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
