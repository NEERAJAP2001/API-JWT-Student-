package com.example.student.security;

import org.apache.catalina.User;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import com.example.student.dao.StudentDao;
import com.example.student.entities.Student;
import com.example.student.exceptions.ResourceNotFoundException;

@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private StudentDao userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		// loading user from database by username
		Student user = this.userRepo.findByEmail(username)
				.orElseThrow(() -> new ResourceNotFoundException("User ", " email : " + username, 0));

		return  user;
	}

}
