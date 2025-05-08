package com.security.security.repository;

import com.security.security.entity.MasterUsers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<MasterUsers, Integer> {
    Optional<MasterUsers> findByUserEmail(String email);
}
