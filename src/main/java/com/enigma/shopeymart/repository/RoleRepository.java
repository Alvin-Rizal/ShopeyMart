package com.enigma.shopeymart.repository;

import com.enigma.shopeymart.constant.ERole;
import com.enigma.shopeymart.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,String> {
    Optional<Role> findByName(ERole name);
}
