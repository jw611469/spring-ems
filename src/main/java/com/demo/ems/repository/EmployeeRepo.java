package com.demo.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.ems.entity.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee,String>{
 
}
