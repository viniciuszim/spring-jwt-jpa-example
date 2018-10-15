package com.example.spring_jwt_jpa.repository;

import com.example.spring_jwt_jpa.model.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Created by viniciuszim on 13/10/18.
 */
@Repository
public interface UserAppRepository extends JpaRepository<UserApp, Long> {

    Optional<UserApp> findByEmail(String email);

    Optional<UserApp> findByLoginOrEmail(String username, String email);

    List<UserApp> findByIdIn(List<Long> userIds);

    Optional<UserApp> findByLogin(String username);

    Boolean existsByLogin(String username);

    Boolean existsByEmail(String email);
}
