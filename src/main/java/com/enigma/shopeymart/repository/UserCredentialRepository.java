package com.enigma.shopeymart.repository;

import com.enigma.shopeymart.entity.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCredentialRepository extends JpaRepository<UserCredential, String> {
}
