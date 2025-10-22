package com.project.self.repo;

import com.project.self.Entity.Role;
import com.project.self.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;
@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {
    Role findByName(String username);
}
