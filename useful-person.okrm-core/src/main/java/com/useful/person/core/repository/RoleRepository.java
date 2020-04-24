package com.useful.person.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.useful.person.core.domain.Role;

public interface RoleRepository extends JpaRepository<Role, String> {

}
