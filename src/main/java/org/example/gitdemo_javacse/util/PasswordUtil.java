package org.example.gitdemo_javacse.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Pattern;

/**
 * 密码工具类
 */
public class PasswordUtil {
    
    /**
     * MD5加密
     */
    public static String md5Encrypt(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5加密失败", e);
        }
    }
    
    /**
     * 验证密码是否符合要求
     * 新密码必须由数字、字母、字符组成，且大于7位
     */
    public static boolean validatePassword(String password) {
        if (password == null || password.length() <= 7) {
            return false;
        }
        
        // 检查是否包含数字
        boolean hasDigit = Pattern.compile("[0-9]").matcher(password).find();
        // 检查是否包含字母
        boolean hasLetter = Pattern.compile("[a-zA-Z]").matcher(password).find();
        // 检查是否包含特殊字符
        boolean hasSpecialChar = Pattern.compile("[^a-zA-Z0-9]").matcher(password).find();
        
        return hasDigit && hasLetter && hasSpecialChar;
    }
    
    /**
     * 验证密码是否匹配
     */
    public static boolean matches(String rawPassword, String encodedPassword) {
        return md5Encrypt(rawPassword).equals(encodedPassword);
    }
}

