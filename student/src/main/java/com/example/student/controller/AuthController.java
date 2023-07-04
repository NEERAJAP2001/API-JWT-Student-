package com.example.student.controller;


import java.security.Principal;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.student.Payloads.JwtAuthRequest;
import com.example.student.Payloads.JwtAuthResponse;
import com.example.student.Payloads.StudentDto;
import com.example.student.dao.StudentDao;
import com.example.student.entities.Student;
import com.example.student.exceptions.ApiException;
import com.example.student.security.JwtTokenHelper;
import com.example.student.services.StudentService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/v1/auth/")
public class AuthController {

	@Autowired
	private JwtTokenHelper jwtTokenHelper;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private StudentService studentservice;

	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request) throws Exception {
		
		
		System.out.println("cam here first");
		this.authenticate(request.getUsername(), request.getPassword());
		
		System.out.println("came here line 52");
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());
		
		System.out.println("came here line 54");
		String token = this.jwtTokenHelper.generateToken(userDetails);

		JwtAuthResponse response = new JwtAuthResponse();
		response.setToken(token);
		response.setUser(this.mapper.map((Student) userDetails, StudentDto.class));
		return new ResponseEntity<JwtAuthResponse>(response, HttpStatus.OK);
	}

	private void authenticate(String username, String password) throws Exception {

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,
				password);

		try {

			this.authenticationManager.authenticate(authenticationToken);

		} catch (BadCredentialsException e) {
			System.out.println("Invalid Detials !!");
			throw new ApiException("Invalid username or password !!");
		}
		catch (Exception h) {
			System.out.println(h);
		}

	}

	// register new user api

	@PostMapping("/register")
	public ResponseEntity<StudentDto> registerUser(@Valid @RequestBody StudentDto userDto) {
		StudentDto registeredUser = this.studentservice.registerNewUser(userDto);
		return new ResponseEntity<StudentDto>(registeredUser, HttpStatus.CREATED);
	}
	
	@PostMapping("/registerAdmin")
	public ResponseEntity<StudentDto> registerAdmin(@Valid @RequestBody StudentDto userDto) {
		StudentDto registeredUser = this.studentservice.registerNewAdmin(userDto);
		return new ResponseEntity<StudentDto>(registeredUser, HttpStatus.CREATED);
	}

	// get loggedin user data
	@Autowired
	private StudentDao userRepo;
	@Autowired
	private ModelMapper mapper;

	@GetMapping("/current-user/")
	public ResponseEntity<StudentDto> getUser(Principal principal) {
		Student user = this.userRepo.findByEmail(principal.getName()).get();
		return new ResponseEntity<StudentDto>(this.mapper.map(user, StudentDto.class), HttpStatus.OK);
	}
	
	


}
