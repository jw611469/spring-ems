package com.demo.ems.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.ems.entity.DemoCase;
import com.demo.ems.repository.DemoCaseRepo;

@Service
public class DemoCaseService {

    @Autowired
    DemoCaseRepo repository;

    public void addCase(DemoCase demoCase){
        repository.save(demoCase);
    }

    public DemoCase getDemoCaseById(String case_id){
        return repository.findById(case_id).get();
    }

    public List<DemoCase> getDemoCaseByUsername(String username){
        return repository.findDemoCaseByUsername(username);
    }
    
}
