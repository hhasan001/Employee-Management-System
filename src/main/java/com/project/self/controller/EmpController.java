package com.project.self.controller;

import com.project.self.Entity.Department;
import com.project.self.Entity.Employee;
import com.project.self.service.DepService;
import com.project.self.service.EmpService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
public class EmpController {

    public final EmpService empService;
    public final DepService depService ;

    public EmpController(EmpService empService, DepService depService) {
        this.empService = empService;
        this.depService = depService;
    }

    @GetMapping("/api/emp/all")
    public List<Employee> getAll(){
        return empService.getAll();
    }
    @GetMapping("/api/emp/getbysalary")
    public List<Employee> getAllBySalary(){
        return empService.getAllBySalary();
    }

    //Get by ID
    @GetMapping("/api/emp/{id}")
    public Optional<Employee> getById(@PathVariable Long id){
        return empService.getById(id);
    }
    @GetMapping("/api/emp/dep/{Empid}")
    public  ResponseEntity<?> getDepofEmp(@PathVariable Long Empid){
        return empService.getDepbyEmpid(Empid);
    }

    @GetMapping("/api/emp/search")
    public ResponseEntity<?> getEmployeeName(@RequestParam String name){
        return empService.getEmpbyName(name);
    }

    @GetMapping("/api/emp/searchByemail")
    public ResponseEntity<?> getEmployeebyemail(@RequestParam String email) {
        return empService.getEmpbyemail(email);
    }

    @GetMapping("/api/emp/salary")
    public ResponseEntity<?> SearchSalary(@RequestParam Long min, @RequestParam Long max){
        return empService.SearchBySalary(min, max);
    }

    @PostMapping("/api/emp/dep/{depid}")
    public void CreateEmployee(@PathVariable Long depid,@RequestBody Employee employee){
        Department dept = depService.getDepartmentById(depid)
                .orElseThrow(() -> new RuntimeException("Department not found"));

        // 2️⃣ Assign it to the employee
        employee.setDepartment(dept);
        empService.addEmployee(employee);
    }

    @PutMapping("/api/emp/{id}")
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

    @DeleteMapping("/api/emp/{id}")
     public HttpStatus deleteById(@PathVariable Long id){

        return empService.deleteEmployee(id);
    }


}
