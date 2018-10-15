package com.example.spring_jwt_jpa.repository;

import com.example.spring_jwt_jpa.model.Role;
import com.example.spring_jwt_jpa.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by viniciuszim on 13/10/18.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(RoleName roleName);

}
