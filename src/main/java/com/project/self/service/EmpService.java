package com.project.self.service;

import com.project.self.Entity.Department;
import com.project.self.Entity.Employee;
import com.project.self.repo.DepRepo;
import com.project.self.repo.EmpRepo;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class EmpService {

    public final EmpRepo empRepo;
    public final DepRepo depRepo;
    public EmpService(EmpRepo empRepo, DepRepo depRepo) {
        this.empRepo = empRepo;
        this.depRepo = depRepo;
    }
    public List<Employee> getAll(){
        return empRepo.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }
    public List<Employee> getAllBySalary(){
        return empRepo.findAll(Sort.by(Sort.Direction.ASC, "salary"));
    }
    public Optional<Employee> getById(Long id){
        return empRepo.findById(id);
    }

    public Employee saveAll(Employee employee){
        return empRepo.save(employee);

    }

    public Employee addEmployee(Employee employee){
        return empRepo.save(employee);
    }

    public HttpStatus deleteEmployee(Long id){
        if(!empRepo.existsById(id)){
            return HttpStatus.NOT_FOUND;
        }
        empRepo.deleteById(id);
        return HttpStatus.OK;
    }

    public ResponseEntity<?> getDepbyEmpid(@PathVariable Long Empid){
        Optional<Employee> emp=empRepo.findById(Empid);
        if(emp.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Employee not found with ID: " + Empid);
        }
        Employee employee = emp.get();
        Department department = employee.getDepartment();
        if(department==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Department not found with ID: " + Empid);
        }
        return ResponseEntity.ok(department.getDepName());
    }

    public ResponseEntity<?> getEmpbyName(String name){
        List<Employee> emp=empRepo.findByNameContainingIgnoreCase(name);
        if(emp.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No employees exists with the given name");
        }
        return ResponseEntity.ok(emp);
    }
    public ResponseEntity<?> getEmpbyemail(String email){
        List<Employee> emp=empRepo.findByEmailIgnoreCase(email);
        if(emp.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No employees exists with the given name");
        }
        return ResponseEntity.ok(emp);
    }

    public ResponseEntity<?>SearchBySalary(Long min, Long max){
        List<Employee> emp=empRepo.findBySalaryBetween(min, max);
        if(emp.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No employees found between this range");
        }
        return ResponseEntity.ok(emp);
    }



}
