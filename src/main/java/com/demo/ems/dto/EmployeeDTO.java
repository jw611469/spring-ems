package com.demo.ems.dto;

import java.time.LocalDateTime;

import com.demo.ems.entity.Employee;

public class EmployeeDTO {

    private String username;

    private String name;

    private Employee.Role role;
    
    private LocalDateTime lastLogin;

    public EmployeeDTO(String name,String username,Employee.Role role,LocalDateTime lastLogin){
        this.name = name;
        this.username = username;
        this.role = role;
        this.lastLogin = lastLogin;
    }

    public EmployeeDTO(Employee employee){
        this.name = employee.getName();
        this.username = employee.getUsername();
        this.role = employee.getRole();
        this.lastLogin = employee.getLastLogin();
    }

    public String getUsername(){
        return username;
    }

    public Employee.Role getRole(){
        return role;
    }

    public String getName(){
        return name;
    }

    public LocalDateTime getLastLogin(){
        return lastLogin;
    }

    public void setRole(Employee.Role role){
        this.role = role;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setLastLogin(LocalDateTime localDateTime){
        this.lastLogin = localDateTime;
    }
}
