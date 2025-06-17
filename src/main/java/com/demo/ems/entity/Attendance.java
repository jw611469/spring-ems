package com.demo.ems.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "attendance")
public class Attendance {

    public Attendance(){

    }

    public Attendance(String username,LocalDateTime timestamp){
        this.username = username;
        this.timestamp = timestamp;
    }

    public Attendance(String username,LocalDateTime timestamp,String note){
        this.username = username;
        this.timestamp = timestamp;
        this.note = note;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    @Column(name = "note")
    private String note;

    public String getUsername(){
        return this.username;
    }

    public LocalDateTime getLocalDateTime(){
        return this.timestamp;
    }

    public String getNote(){
        return this.note;
    }

    public void setNote(String note){
        this.note = note;
    }

}
