package com.demo.ems;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

import com.demo.ems.service.EmployeeService;


@Component
public class EmsEventListener {

    @Autowired
    EmployeeService employeeService;

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady() {
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