package com.example.student.Payloads;

import lombok.Data;

@Data
public class JwtAuthResponse {

	private String token;
	
	private StudentDto user;
}
