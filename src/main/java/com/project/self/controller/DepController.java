package com.project.self.controller;

import com.project.self.Entity.Department;
import com.project.self.Entity.Employee;
import com.project.self.service.DepService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController

public class DepController {

    private DepService depService;

    public DepController(DepService depService) {
        this.depService = depService;
    }

    @GetMapping("/api/dep/all")
    public List<Department> getAllDepartments(){
        return depService.getAllDep();
    }

    @GetMapping("/api/dep/emp/{depid}")
    public ResponseEntity<?> getEmpinDep(@PathVariable Long depid){
        return depService.findEmpindep(depid);
    }


    @PostMapping("api/dep/add")
    public void AddDepartment(@RequestBody Department department){
        depService.addDep(department);
    }


}
