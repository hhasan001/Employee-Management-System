package com.project.self.controller;

import com.project.self.Model.Employee;
import com.project.self.service.EmpService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmpController {

    public final EmpService empService;

    public EmpController(EmpService empService) {
        this.empService = empService;
    }

    @GetMapping("/api/all")
    public List<Employee> getAll(){
        return empService.getAll();
    }

    @PostMapping("/api/add")
    public void CreateEmployee(@RequestBody Employee employee){
        empService.addEmployee(employee);
        //System.out.println("added");
    }


}
