package com.demo.ems.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.demo.ems.dto.EmployeeDTO;
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

    public List<Employee> getEmployee(){
        return repository.findAll();
    }

    public Employee getEmployee(String username){
        return repository.findById(username).orElse(null);
    }

    public List<EmployeeDTO> getEmployeeDTO(){
        return repository.findAll().stream().map(employee->new EmployeeDTO(employee)).collect(Collectors.toList());
    }

    public void updateLastLogin(String username){
        repository.findById(username).ifPresent(employee->{
            employee.setLastLogin(LocalDateTime.now());
            repository.save(employee);
        });
    }

    public void updatePassword(String username,String password){
        repository.findById(username).ifPresent(employee->{
            employee.setPassword(passwordEncoder.encode(password));
            repository.save(employee);
        });
    }

    public void updateName(String username,String name){
        repository.findById(username).ifPresent(employee->{
            employee.setName(name);
            repository.save(employee);
        });
    }

    public void updateRole(String username,Employee.Role role){
        repository.findById(username).ifPresent(employee->{
            employee.setRole(role);
            repository.save(employee);
        });
    }
}
