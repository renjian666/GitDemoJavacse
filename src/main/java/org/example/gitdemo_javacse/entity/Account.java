package org.example.gitdemo_javacse.entity;

import jakarta.persistence.*;
import java.io.Serializable;

/**
 * 账号实体类
 */
@Entity
@Table(name = "account")
public class Account implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "username", unique = true, nullable = false, length = 50)
    private String username; // 账号名字（学号）
    
    @Column(name = "password", nullable = false, length = 64)
    private String password; // 密码（MD5加密）
    
    @Column(name = "role", nullable = false, length = 20)
    private String role; // 角色：student 或 admin
    
    @Column(name = "first_login", nullable = false)
    private Boolean firstLogin = true; // 是否首次登录
    
    public Account() {
    }
    
    public Account(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.firstLogin = true;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getRole() {
        return role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }
    
    public Boolean getFirstLogin() {
        return firstLogin;
    }
    
    public void setFirstLogin(Boolean firstLogin) {
        this.firstLogin = firstLogin;
    }
}

