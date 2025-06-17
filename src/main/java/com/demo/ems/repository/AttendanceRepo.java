package com.demo.ems.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.demo.ems.entity.Attendance;

@Repository
public interface AttendanceRepo extends JpaRepository<Attendance,Long>{

    @Query(value = "SELECT * FROM attendance WHERE username = ?1 AND timestamp BETWEEN ?2 AND ?3", nativeQuery = true)
    List<Attendance> findAttendanceByTimestamp(String username,LocalDateTime start,LocalDateTime end);

    @Query(value = "SELECT * FROM attendance WHERE username = ?1 AND timestamp = ?2 LIMIT 1", nativeQuery = true)
    Attendance findAttendanceByTimestamp(String username,LocalDateTime localDateTime);
    
}