package com.project.self.repo;

import com.project.self.Entity.Employee;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpRepo extends JpaRepository<Employee, Long> {
    List<Employee> findByNameContainingIgnoreCase(String name);
    List<Employee> findByEmailIgnoreCase(String email);
    List<Employee> findBySalaryBetween(Long min, Long max);

}
