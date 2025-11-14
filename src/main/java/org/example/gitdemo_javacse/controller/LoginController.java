package org.example.gitdemo_javacse.controller;

import org.example.gitdemo_javacse.entity.Account;
import org.example.gitdemo_javacse.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 登录控制器
 */
@RestController
@RequestMapping("/api/login")
@CrossOrigin(origins = "*")
public class LoginController {
    
    @Autowired
    private LoginService loginService;
    
    /**
     * 学生登录
     */
    @PostMapping("/student")
    public ResponseEntity<Map<String, Object>> studentLogin(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");
        
        if (username == null || password == null) {
            Map<String, Object> result = new HashMap<>();
            result.put("success", false);
            result.put("message", "用户名和密码不能为空");
            return ResponseEntity.badRequest().body(result);
        }
        
        Map<String, Object> result = loginService.login(username, password);
        return ResponseEntity.ok(result);
    }
    
    /**
     * 修改密码
     */
    @PostMapping("/change-password")
    public ResponseEntity<Map<String, Object>> changePassword(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String oldPassword = request.get("oldPassword");
        String newPassword = request.get("newPassword");
        
        if (username == null || oldPassword == null || newPassword == null) {
            Map<String, Object> result = new HashMap<>();
            result.put("success", false);
            result.put("message", "参数不能为空");
            return ResponseEntity.badRequest().body(result);
        }
        
        Map<String, Object> result = loginService.changePassword(username, oldPassword, newPassword);
        return ResponseEntity.ok(result);
    }
    
    /**
     * 检查是否需要修改密码
     */
    @GetMapping("/check-first-login")
    public ResponseEntity<Map<String, Object>> checkFirstLogin(@RequestParam String username) {
        Map<String, Object> result = new HashMap<>();
        
        var accountOpt = loginService.findByUsername(username);
        if (accountOpt.isEmpty()) {
            result.put("success", false);
            result.put("message", "账号不存在");
            return ResponseEntity.ok(result);
        }
        
        Account account = accountOpt.get();
        result.put("success", true);
        result.put("firstLogin", account.getFirstLogin());
        
        return ResponseEntity.ok(result);
    }
}

