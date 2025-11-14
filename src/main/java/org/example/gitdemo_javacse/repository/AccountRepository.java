package org.example.gitdemo_javacse.repository;

import org.example.gitdemo_javacse.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * 账号Repository接口
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    
    /**
     * 根据用户名查找账号
     */
    Optional<Account> findByUsername(String username);
    
    /**
     * 检查用户名是否存在
     */
    boolean existsByUsername(String username);
}

