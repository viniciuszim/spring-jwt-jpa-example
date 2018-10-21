package com.example.spring_jwt_jpa.controller;

import com.example.spring_jwt_jpa.enumerador.RoleName;
import com.example.spring_jwt_jpa.exception.AppException;
import com.example.spring_jwt_jpa.exception.ResourceNotFoundException;
import com.example.spring_jwt_jpa.model.Device;
import com.example.spring_jwt_jpa.model.Role;
import com.example.spring_jwt_jpa.model.User;
import com.example.spring_jwt_jpa.payload.ApiResponse;
import com.example.spring_jwt_jpa.payload.JwtAuthenticationResponse;
import com.example.spring_jwt_jpa.payload.LoginRequest;
import com.example.spring_jwt_jpa.payload.SignUpRequest;
import com.example.spring_jwt_jpa.repository.DeviceRepository;
import com.example.spring_jwt_jpa.repository.RoleRepository;
import com.example.spring_jwt_jpa.repository.UserRepository;
import com.example.spring_jwt_jpa.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collections;

/**
 * Created by viniciuszim on 13/10/18.
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    DeviceRepository deviceRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    @PostMapping("/admin/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);

        // SALVAR O DEVICE TOKEN

        if (loginRequest.getDeviceToken() != null && !"".equals(loginRequest.getDeviceToken())) {

            long userId = tokenProvider.getUserIdFromJWT(jwt);

            User user = userRepository.findById(userId).orElseThrow(
                    () -> new ResourceNotFoundException("User", "id", userId)
            );

            Device device = deviceRepository.findByToken(loginRequest.getDeviceToken()).orElse(
                    new Device()
            );
            if (device.getId() == 0) {

                device.setToken(loginRequest.getDeviceToken());
                device.setPlatform(loginRequest.getPlatform());

                deviceRepository.save(device);
            }

            if (user.getDevices() != null && user.getDevices().size() > 0) {
                user.getDevices().add(device);
            } else {
                user.setDevices(Collections.singleton(device));
            }

            userRepository.save(user);

        }

        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @PostMapping("/admin/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if(userRepository.existsByLogin(signUpRequest.getUsername())) {
            return new ResponseEntity(new ApiResponse(false, "Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        User user = new User(signUpRequest.getName(), signUpRequest.getUsername(),
                signUpRequest.getEmail(), signUpRequest.getPassword());

        user.setSenha(passwordEncoder.encode(user.getSenha()));

        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new AppException("User Role not set."));

        user.setRoles(Collections.singleton(userRole));

        User result = userRepository.save(user);

        return ResponseEntity.ok(result);
    }

    @PostMapping("/app/signin")
    public ResponseEntity<?> authenticateUserApp(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @PostMapping("/app/signup")
    public ResponseEntity<?> registerUserApp(@Valid @RequestBody SignUpRequest signUpRequest) {
        if(userRepository.existsByLoginAndTipo(signUpRequest.getUsername(), 2)) {
            return new ResponseEntity(new ApiResponse(false, "Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        if(userRepository.existsByEmailAndTipo(signUpRequest.getEmail(), 2)) {
            return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        User user = new User(signUpRequest.getName(), signUpRequest.getUsername(),
                signUpRequest.getEmail(), signUpRequest.getPassword());

        user.setSenha(passwordEncoder.encode(user.getSenha()));

        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new AppException("User Role not set."));

        user.setRoles(Collections.singleton(userRole));

        User result = userRepository.save(user);

        return ResponseEntity.ok(result);
    }

}
