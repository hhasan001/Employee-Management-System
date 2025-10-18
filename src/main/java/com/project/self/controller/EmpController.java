package com.project.self.controller;

import com.project.self.Model.Employee;
import com.project.self.service.EmpService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

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

    @PutMapping("/api/{id}")
    public ResponseEntity<Employee> updateByid(@PathVariable Long id, @RequestBody Employee newemployee){
            Employee old =empService.getById(id).orElse(null);

            if(old!=null){
                old.setName(newemployee.getName()!=null && newemployee.getName()!=""?newemployee.getName(): old.getName());
                old.setEmail(newemployee.getEmail()!=null && newemployee.getEmail()!=""?newemployee.getEmail(): old.getEmail());
                old.setRole(newemployee.getRole()!=null && newemployee.getRole()!=""?newemployee.getRole(): old.getRole());
                old.setLocation(newemployee.getLocation()!=null && newemployee.getLocation()!=""?newemployee.getLocation(): old.getLocation());
                old.setSalary(newemployee.getSalary()!=null?newemployee.getSalary(): old.getSalary());
                empService.saveAll(old);
                return new ResponseEntity<>(old, HttpStatus.CREATED);
            }
            //
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);


    }


}
