package com.example.spring_jwt_jpa.repository;

import com.example.spring_jwt_jpa.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by viniciuszim on 13/10/18.
 */
@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {

    Optional<Device> findByToken(String token);

}
