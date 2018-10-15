package com.example.spring_jwt_jpa.controller;

import com.example.spring_jwt_jpa.model.UserApp;
import com.example.spring_jwt_jpa.payload.UserIdentityAvailability;
import com.example.spring_jwt_jpa.repository.UserAppRepository;
import com.example.spring_jwt_jpa.util.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by viniciuszim on 13/10/18.
 */

@RestController
@RequestMapping("/api/userapp")
public class UserAppController {

    @Autowired
    private UserAppRepository userAdmRepository;

    @GetMapping
    public Page<UserApp> listar(@RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {


        // Retrieve Polls
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "createdAt");
        Page<UserApp> users = userAdmRepository.findAll(pageable);

        return users;
    }

    @GetMapping("/checkLoginAvailability")
    public UserIdentityAvailability checkLoginAvailability(@RequestParam(value = "login") String login) {
        Boolean isAvailable = !userAdmRepository.existsByLogin(login);
        return new UserIdentityAvailability(isAvailable);
    }

    @GetMapping("/checkEmailAvailability")
    public UserIdentityAvailability checkEmailAvailability(@RequestParam(value = "email") String email) {
        Boolean isAvailable = !userAdmRepository.existsByEmail(email);
        return new UserIdentityAvailability(isAvailable);
    }

}
