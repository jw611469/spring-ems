package com.demo.ems.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "demo_case")
public class DemoCase {

    public enum Status{
        assigned,unassigned,closed,others
    }

    public DemoCase(){

    }

    @Id
    @Column(name = "case_Id")
    private String caseId;

    @Column(name = "username")
    private String username;

    @Column(name = "status")
    private Status status;

    @Column(name = "open_time")
    private LocalDateTime openTime;

    @Column(name = "description")
    private String description;

    @OneToOne
    @JoinColumn(name = "customer",referencedColumnName = "name")
    @NotFound(action = NotFoundAction.IGNORE)
    private Customer customer;

    public String getCaseId(){
        return this.caseId;
    }

    public String getUsername(){
        return this.username;
    }

    public Status getStatus(){
        return this.status;
    }

    public LocalDateTime getOpenTime(){
        return this.openTime;
    }

    public String getDescription(){
        return this.description;
    }

    public Customer getCustomer(){
        return this.customer;
    }

    public void setCaseId(String case_Id){
        this.caseId = case_Id;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public void setStatus(Status status){
        this.status = status;
    }

    public void setOpenTime(LocalDateTime openTime){
        this.openTime = openTime;
    }

    public void setOpenTime(String openTime){
        this.openTime = LocalDate.parse(openTime).atStartOfDay();
    }

    public void setCustomer(Customer customer){
        this.customer = customer;
    }

    public void setDescription(String description){
        this.description = description;
    }

}
