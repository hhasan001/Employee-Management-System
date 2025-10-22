package com.project.self.service;

import com.project.self.Entity.Department;
import com.project.self.Entity.Employee;
import com.project.self.repo.DepRepo;
import com.project.self.repo.EmpRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DepService {

    public DepRepo depRepo;


    public DepService(DepRepo depRepo) {
        this.depRepo = depRepo;
    }

    public List<Department> getAllDep(){
        return depRepo.findAll();
    }
    public Optional<Department> getDepartmentById(Long depid){
        return depRepo.findById(depid);
    }


    public void addDep(Department department){
        depRepo.save(department);
    }

    @Transactional
    public ResponseEntity<?>findEmpindep(Long depid){
        Optional<Department> department = depRepo.findById(depid);
        if(department.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Department exist for the given id");
        }
        Department department1 = department.get();
        List<Employee> employees =department1.getEmployees();
        if(employees.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Employees exist under this department");
        }
        return ResponseEntity.ok(employees);
    }
}
