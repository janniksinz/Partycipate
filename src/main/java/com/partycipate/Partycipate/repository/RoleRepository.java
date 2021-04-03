package com.partycipate.Partycipate.repository;

import com.partycipate.Partycipate.model.Role;
import com.partycipate.Partycipate.model.RoleName;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//ToDo Consider using JpaRepository<Role,Integer> instead?
@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
    Optional<Role> findByName(RoleName roleName);
}
