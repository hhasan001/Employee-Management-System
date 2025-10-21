package com.project.self.Entity;

import jakarta.persistence.*;
import lombok.NonNull;

@Entity
//@Data
public class Employee {
    public Long getEmpid() {
        return Empid;
    }

    public void setEmpid(Long empid) {
        this.Empid = empid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Empid;
    @NonNull
    private String name;
    private Long salary;
    @NonNull
    private String role;
    @NonNull
    private String email;
    private String location;

    @ManyToOne
    @JoinColumn(name = "department_id") // foreign key
    private Department department;

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
