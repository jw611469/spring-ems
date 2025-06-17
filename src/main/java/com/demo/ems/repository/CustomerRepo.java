package com.demo.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.ems.entity.Customer;

@Repository
public interface CustomerRepo extends JpaRepository<Customer,String>{

}
