package com.project.self.service;

import com.project.self.Model.Employee;
import com.project.self.repo.EmpRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpService {

    public final EmpRepo empRepo;

    public EmpService(EmpRepo empRepo) {
        this.empRepo = empRepo;
    }
    public List<Employee> getAll(){
        return empRepo.findAll();
    }

    public Employee addEmployee(Employee employee){
        return empRepo.save(employee);
    }

}
