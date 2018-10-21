package com.example.spring_jwt_jpa.model;

import javax.persistence.*;

/**
 * Created by viniciuszim on 13/10/18.
 */
@Entity
@Table(name = "devices")
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 200)
    private String token;

    @Column(length = 10)
    private String platform;

    public Device() {

    }

    public Device(String token) {
        this.token = token;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

}
