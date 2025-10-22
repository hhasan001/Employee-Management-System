package com.project.self.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Depid;

    private String depName;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    @JsonIgnore // prevents infinite recursion when serializing Employee -> Department -> Employee
    private List<Employee> employees;

    // Getters and setters
    public Long getDepid() { return Depid; }
    public void setDepid(Long depid) { this.Depid = depid; }

    public String getDepName() { return depName; }
    public void setDepName(String depName) { this.depName = depName; }

    public List<Employee> getEmployees() { return employees; }
    public void setEmployees(List<Employee> employees) { this.employees = employees; }
}
