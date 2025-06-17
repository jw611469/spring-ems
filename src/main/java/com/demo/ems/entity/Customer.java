package com.demo.ems.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer {

    public Customer(){

    }

    public Customer(String name,String telephone,String mobile,String address){
        this.name = name;
        this.telephone = telephone;
        this.mobile = mobile;
        this.address = address;
    }

    @Id
    @Column(name = "name")
    private String name;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "address")
    private String address;

    public String getName(){
        return this.name;
    }

    public String getTelephone(){
        return this.telephone;
    }

    public String getMobile(){
        return this.telephone;
    }

    public String getAddress(){
        return this.address;
    }

}
