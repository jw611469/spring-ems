package com.demo.ems;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

import com.demo.ems.entity.Employee;
import com.demo.ems.service.EmployeeService;


@Component
public class EmsEventListener {

    @Autowired
    private Environment environment;

    @Autowired
    private EmployeeService employeeService;

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady() {
        String admin = environment.getProperty("ems.admin.default.username");
        String password = environment.getProperty("ems.admin.default.password");
        if(employeeService.getEmployee("Administrator")==null){
            employeeService.register("admin", admin, password, Employee.Role.ADMIN);
        }
    }

    @EventListener(ContextClosedEvent.class)
    public void onShutdown() {
        System.out.println("Application is shutting down. Cleaning up resources...");
    }

    @EventListener
    public void onSuccess(AuthenticationSuccessEvent success) {
        employeeService.updateLastLogin(success.getAuthentication().getName());
    }

}