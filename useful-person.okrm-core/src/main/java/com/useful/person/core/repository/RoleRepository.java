package com.useful.person.core.repository;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import com.useful.person.core.domain.Role;

public interface RoleRepository extends JpaRepository<Role, String> {

    Role findByRolename(String rolename);

    List<Role> findAll(Specification<Role> spec);

}
