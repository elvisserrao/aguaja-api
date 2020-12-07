package com.aguaja.api.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.Any;
import org.hibernate.annotations.AnyMetaDef;
import org.hibernate.annotations.MetaValue;

import com.aguaja.api.domain.interfaces.HasAddress;

@Entity
@Table(name = "tb_address")
public class Address implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String street;
	private String neighborhood;
	private Integer number;
	private String complement;

	@Any(metaColumn = @Column(name = "user_type"))
	@AnyMetaDef(idType = "integer", metaType = "string", metaValues = {
	@MetaValue(value = "1", targetEntity = Client.class),
	@MetaValue(value = "2", targetEntity = Seller.class) })
	@JoinColumn(name = "user_id")
	private HasAddress hasAddress;

	public Address() {
		super();
	}

	public Address(Integer id, String street, String neighborhood, Integer number, String complement) {
		this.id = id;
		this.street = street;
		this.neighborhood = neighborhood;
		this.number = number;
		this.complement = complement;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}

	public String getNeighborhood() {
		return neighborhood;
	}
	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getComplement() {
		return complement;
	}
	public void setComplement(String complement) {
		this.complement = complement;
	}

	public HasAddress getHasAddress() {
		return hasAddress;
	}

	public void setHasAddress(HasAddress hasAddress) {
		this.hasAddress = hasAddress;
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
		Address other = (Address) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
