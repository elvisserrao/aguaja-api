package com.aguaja.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aguaja.api.services.JwtUserDetails;
import com.aguaja.api.services.JwtUserDetailsService;
import com.aguaja.api.config.JwtTokenUtil;
import com.aguaja.api.domain.JwtRequest;
import com.aguaja.api.domain.JwtResponse;
import com.aguaja.api.domain.SellerDTO;
import com.aguaja.api.domain.ClientDTO;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				authenticationRequest.getUsername(), authenticationRequest.getPassword()));

		JwtUserDetails userDetails = (JwtUserDetails) authentication.getPrincipal();
		final String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new JwtResponse(token, userDetails.getId(), userDetails.getUsername(),
				userDetails.getEmail(), userDetails.getAdmin(), userDetails.getRole()));

	}

	@RequestMapping(value = "/client/register", method = RequestMethod.POST)
	public ResponseEntity<?> saveUser(@RequestBody ClientDTO client) throws Exception {
		return ResponseEntity.ok(userDetailsService.save(client));
	}

	@RequestMapping(value = "/seller/register", method = RequestMethod.POST)
	public ResponseEntity<?> saveUser(@RequestBody SellerDTO seller) throws Exception {
		return ResponseEntity.ok(userDetailsService.save(seller));
	}
}
