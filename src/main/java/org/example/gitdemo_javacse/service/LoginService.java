package org.example.gitdemo_javacse.service;

import org.example.gitdemo_javacse.entity.Account;
import org.example.gitdemo_javacse.repository.AccountRepository;
import org.example.gitdemo_javacse.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * 登录服务类
 */
@Service
@Transactional
public class LoginService {
    
    @Autowired
    private AccountRepository accountRepository;
    
    /**
     * 学生登录
     * @param username 学号
     * @param password 密码
     * @return 登录结果
     */
    public Map<String, Object> login(String username, String password) {
        Map<String, Object> result = new HashMap<>();
        
        Optional<Account> accountOpt = accountRepository.findByUsername(username);
        if (accountOpt.isEmpty()) {
            result.put("success", false);
            result.put("message", "账号不存在");
            return result;
        }
        
        Account account = accountOpt.get();
        String encryptedPassword = PasswordUtil.md5Encrypt(password);
        
        if (!account.getPassword().equals(encryptedPassword)) {
            result.put("success", false);
            result.put("message", "密码错误");
            return result;
        }
        
        result.put("success", true);
        result.put("message", "登录成功");
        result.put("account", account);
        result.put("firstLogin", account.getFirstLogin());
        
        return result;
    }
    
    /**
     * 修改密码
     * @param username 学号
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @return 修改结果
     */
    public Map<String, Object> changePassword(String username, String oldPassword, String newPassword) {
        Map<String, Object> result = new HashMap<>();
        
        Optional<Account> accountOpt = accountRepository.findByUsername(username);
        if (accountOpt.isEmpty()) {
            result.put("success", false);
            result.put("message", "账号不存在");
            return result;
        }
        
        Account account = accountOpt.get();
        
        // 验证旧密码
        String encryptedOldPassword = PasswordUtil.md5Encrypt(oldPassword);
        if (!account.getPassword().equals(encryptedOldPassword)) {
            result.put("success", false);
            result.put("message", "旧密码错误");
            return result;
        }
        
        // 验证新密码格式
        if (!PasswordUtil.validatePassword(newPassword)) {
            result.put("success", false);
            result.put("message", "新密码必须由数字、字母、特殊字符组成，且大于7位");
            return result;
        }
        
        // 更新密码
        String encryptedNewPassword = PasswordUtil.md5Encrypt(newPassword);
        account.setPassword(encryptedNewPassword);
        account.setFirstLogin(false); // 标记已修改密码
        accountRepository.save(account);
        
        result.put("success", true);
        result.put("message", "密码修改成功");
        
        return result;
    }
    
    /**
     * 根据用户名查找账号
     */
    public Optional<Account> findByUsername(String username) {
        return accountRepository.findByUsername(username);
    }
}

