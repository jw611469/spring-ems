package com.demo.ems.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.demo.ems.entity.Employee;
import com.demo.ems.repository.EmployeeRepo;

@Service
public class EmployeeService implements UserDetailsService{

    @Autowired
    private EmployeeRepo repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Employee loadUserByUsername(String username) throws UsernameNotFoundException{
        Employee user = repository.findById(username).get();
        return user;
    }

    public boolean register(String name,String username,String password,Employee.Role role) {
        if (repository.findById(username).isPresent()) {
            return false;
        }
        Employee employee = new Employee(
            name,
            username,
            passwordEncoder.encode(password),
            role
        );
        repository.save(employee);
        return true;
    }

    public void updateLastLogin(String username){
        repository.findById(username).ifPresent(employee->{
            employee.setLastLogin(LocalDateTime.now());
            repository.save(employee);
        });
    }
}
