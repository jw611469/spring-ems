package com.demo.ems.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.ems.entity.Customer;
import com.demo.ems.repository.CustomerRepo;

@Service
public class CustomerService {

    @Autowired
    CustomerRepo repository;

    public void addCustomer(String name,String telephone,String mobile,String address){
        repository.save(new Customer(name,telephone,mobile,address));
    }

    public Customer getCustomer(String name){
        return repository.findById(name).get();
    }

}
