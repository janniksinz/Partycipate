package com.partycipate.Partycipate.repository;

import com.partycipate.Partycipate.model.Role;
import com.partycipate.Partycipate.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//ToDo Consider using JpaRepository<Role,Integer> instead?
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(RoleName roleName);
}
