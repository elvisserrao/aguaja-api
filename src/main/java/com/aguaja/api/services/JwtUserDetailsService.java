package com.aguaja.api.services;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.aguaja.api.repositories.ClientRepository;
import com.aguaja.api.repositories.SellerRepository;
import com.aguaja.api.domain.Client;
import com.aguaja.api.domain.Seller;
import com.aguaja.api.domain.ClientDTO;
import com.aguaja.api.domain.SellerDTO;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private SellerRepository sellerRepository;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if (clientRepository.findByUsername(username).isPresent()) {
			Client client = clientRepository.findByUsername(username)
					.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

			return new org.springframework.security.core.userdetails.User(client.getUsername(), client.getPassword(),
					new ArrayList<>());
		} else {
			Seller seller = sellerRepository.findByUsername(username)
					.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

			return new org.springframework.security.core.userdetails.User(seller.getUsername(), seller.getPassword(),
					new ArrayList<>());
		}

	}

	public Client save(ClientDTO client) {
		Client newClient = new Client();
		newClient.setName(client.getName());
		newClient.setGender(client.getGender());
		newClient.setEmail(client.getEmail());
		newClient.setPhone(client.getPhone());
		newClient.setUsername(client.getUsername());
		newClient.setPassword(bcryptEncoder.encode(client.getPassword()));
		return clientRepository.save(newClient);
	}

	public Seller save(SellerDTO seller) {
		Seller newSeller = new Seller();
		newSeller.setName(seller.getName());
		newSeller.setGender(seller.getGender());
		newSeller.setEmail(seller.getEmail());
		newSeller.setPhone(seller.getPhone());
		newSeller.setUsername(seller.getUsername());
		newSeller.setPassword(bcryptEncoder.encode(seller.getPassword()));
		return sellerRepository.save(newSeller);
	}
}
