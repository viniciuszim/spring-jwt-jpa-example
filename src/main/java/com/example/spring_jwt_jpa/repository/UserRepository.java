package com.example.spring_jwt_jpa.repository;

import com.example.spring_jwt_jpa.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Created by viniciuszim on 13/10/18.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByLoginOrEmail(String username, String email);

    List<User> findByIdIn(List<Long> userIds);

    Optional<User> findByLogin(String username);

    Boolean existsByLogin(String username);

    Boolean existsByEmail(String email);

    Boolean existsByLoginAndTipo(String username, long tipo);

    Boolean existsByEmailAndTipo(String email, long tipo);

}
