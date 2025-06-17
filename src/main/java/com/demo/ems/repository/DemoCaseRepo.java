package com.demo.ems.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.demo.ems.entity.DemoCase;

@Repository
public interface DemoCaseRepo extends JpaRepository<DemoCase,String>{

    @Query(value = "SELECT * FROM demo_case WHERE username = ?1", nativeQuery = true)
    List<DemoCase> findDemoCaseByUsername(String username);

    @Query(value = "SELECT * FROM demo_case INNER JOIN customer on customer.name", nativeQuery = true)
    List<DemoCase> findDemoCaseByCustomer(String customerName);

}
