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

import com.aguaja.api.domain.interfaces.HasPhone;

@Entity
@Table(name = "tb_phone")
public class Phone implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String ddd;
	private String number;
	
	@Any(metaColumn = @Column(name = "user_type"))
	@AnyMetaDef(idType = "integer", metaType = "string", metaValues = {
	@MetaValue(value = "1", targetEntity = Client.class),
	@MetaValue(value = "2", targetEntity = Seller.class) })
	@JoinColumn(name = "user_id")
	private HasPhone hasPhone;

	public Phone() {
		super();
	}

	public Phone(Integer id, String ddd, String number) {
		super();
		this.id = id;
		this.ddd = ddd;
		this.number = number;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getDdd() {
		return ddd;
	}
	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}

	

	public HasPhone getHasPhone() {
		return hasPhone;
	}

	public void setHasPhone(HasPhone hasPhone) {
		this.hasPhone = hasPhone;
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
		Phone other = (Phone) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	} 
}
