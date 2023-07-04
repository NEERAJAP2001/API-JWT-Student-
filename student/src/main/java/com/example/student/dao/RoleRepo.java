package com.example.student.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.student.entities.Role;




public interface RoleRepo  extends JpaRepository<Role, Integer>{

}
