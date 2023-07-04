package com.example.student.services;

import java.util.List;

import com.example.student.Payloads.StudentDto;



import com.example.student.entities.Student;

import jakarta.mail.MessagingException;
import jakarta.validation.Valid;

public interface StudentService {
	
	StudentDto registerNewUser(StudentDto user);
	public List<Student> allstudents();

	public void addStudent(Student student);
	public Student getStudent( int id);
	public Student  updateStudent( int id ,Student student);

	public void deleteStudent(int id);
	StudentDto registerNewAdmin(StudentDto user); 
    String forgotPassword(String email) throws MessagingException;

    String setPassword(String email, String newPassword);

    //String resendOtp(String email) throws MessagingException;

    String verifyOtp(String email, String otp);

}
