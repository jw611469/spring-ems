package com.demo.ems.controller;

import org.springframework.web.bind.annotation.RestController;

import com.demo.ems.entity.DemoCase;
import com.demo.ems.service.AttendanceService;
import com.demo.ems.service.CustomerService;
import com.demo.ems.service.DemoCaseService;
import com.demo.ems.service.EmployeeService;

import jakarta.servlet.http.HttpServletRequest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
public class EmsApi {

    @Autowired
    AttendanceService attendanceService;

    @Autowired
    CustomerService customerService;

    @Autowired
    DemoCaseService demoCaseService;

    @Autowired
    EmployeeService employeeService;

    class EmsResponses{
        private boolean success;
        private String text;
        private Object content;
        public EmsResponses(boolean success,String text,Object content){
            this.success = success;
            this.text = text;
            this.content = content;
        }
        public boolean getSuccess(){
            return this.success;
        }
        public String getText(){
            return this.text;
        }
        public Object getContent(){
            return this.content;
        }
    }

    @PostMapping("employee/attendance")
    public void updateAttendance(Authentication authentication,HttpServletRequest request) {
        String username = authentication.getName();
        String timestamp = request.getParameter("timestamp");
        String note = request.getParameter("note");
        attendanceService.saveAttendance(username, timestamp, note);
    }

    @GetMapping("employee/attendance")
    public EmsResponses getAttendance(Authentication authentication,HttpServletRequest request) {
        String username = authentication.getName();
        String start = request.getParameter("start");
        String end = request.getParameter("end");
        // return attendanceService.getAttendance(username, start, end);
        return new EmsResponses(true, "success", attendanceService.getAttendance(username, start, end));
    }

    @GetMapping("employee/case")
    public EmsResponses getUserDemoCase(Authentication authentication,HttpServletRequest request) {
        String username = authentication.getName();
        return new EmsResponses(true,"success", demoCaseService.getDemoCaseByUsername(username));
    }

    @GetMapping("/customer")
    public EmsResponses getCustomer(Authentication authentication,HttpServletRequest request) {
        String name = request.getParameter("name");
        return new EmsResponses(true, "success",customerService.getCustomer(name));
    }

    @PostMapping("/customer")
    public void postCustomer(Authentication authentication,HttpServletRequest request) {
        String name = request.getParameter("name");
        String telephone = request.getParameter("telephone");
        String mobile = request.getParameter("mobile");
        String address = request.getParameter("address");
        customerService.addCustomer(name, telephone, mobile, address);
    }

    @PostMapping("/case")
    public void updateDemoCase(Authentication authentication,HttpServletRequest request) {
        String username = authentication.getName();
        String caseId = request.getParameter("caseId");
        String openTime = request.getParameter("openTime");
        String customer = request.getParameter("customer");
        String description = request.getParameter("description");
        String status = request.getParameter("status");
        DemoCase demoCase = new DemoCase();
        demoCase.setCaseId(caseId);
        demoCase.setOpenTime(openTime);
        demoCase.setUsername(username);
        demoCase.setDescription(description);
        demoCase.setCustomer(customerService.getCustomer(customer));
        switch (status) {
            case "unassigned":
                demoCase.setStatus(DemoCase.Status.unassigned);
                break;
            case "assigned":
                demoCase.setStatus(DemoCase.Status.assigned);
                break;
            case "closed":
                demoCase.setStatus(DemoCase.Status.closed);
                break;
            default:
                demoCase.setStatus(DemoCase.Status.others);
                break;
        }
        System.out.println(demoCase.getCaseId());
        demoCaseService.addCase(demoCase);
    }

    @GetMapping("/case")
    public EmsResponses getDemoCase(Authentication authentication,HttpServletRequest request) {
        String caseId = request.getParameter("caseId");
        return new EmsResponses(true, "success", demoCaseService.getDemoCaseById(caseId));
    }
    
}
