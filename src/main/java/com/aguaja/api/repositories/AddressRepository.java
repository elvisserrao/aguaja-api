package com.aguaja.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aguaja.api.domain.Address;

public interface AddressRepository extends JpaRepository<Address, Long>{

}
