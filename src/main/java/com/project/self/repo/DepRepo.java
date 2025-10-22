package com.project.self.repo;

import com.project.self.Entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepRepo extends JpaRepository<Department, Long> {
    void save(Optional<Department> dep);
}
