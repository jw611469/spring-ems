package com.demo.ems.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.demo.ems.entity.Attendance;
import com.demo.ems.repository.AttendanceRepo;

@Service
public class AttendanceService {

    @Autowired
    AttendanceRepo repository;

    public void saveAttendance(String username,LocalDateTime timestamp,String note){
        Attendance attendance = repository.findOne(Example.of(new Attendance(username,timestamp))).orElse(null);
        if(attendance==null){
            repository.save(new Attendance(username,timestamp,note));
        }else{
            attendance.setNote(note);
            repository.save(attendance);
        }
    }

    public void saveAttendance(String username,String timestamp,String note){
        saveAttendance(username, LocalDate.parse(timestamp).atStartOfDay(), note);
    }

    public Attendance getAttendance(String username,LocalDateTime timestamp){
        return repository.findAttendanceByTimestamp(username, timestamp);
    }

    public List<Attendance> getAttendance(String username,LocalDateTime start,LocalDateTime end){
        return repository.findAttendanceByTimestamp(username, start,end);
    }

    public List<Attendance> getAttendance(String username,String start,String end){
        return repository.findAttendanceByTimestamp(
            username, 
            LocalDate.parse(start).atStartOfDay(),
            LocalDate.parse(end).atStartOfDay()
        );
    }

}
