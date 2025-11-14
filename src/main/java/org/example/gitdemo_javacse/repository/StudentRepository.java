package org.example.gitdemo_javacse.repository;

import org.example.gitdemo_javacse.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * 学生Repository接口
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    
    /**
     * 根据学号查找学生
     */
    Optional<Student> findByStudentNumber(String studentNumber);
    
    /**
     * 检查学号是否存在
     */
    boolean existsByStudentNumber(String studentNumber);
    
    /**
     * 检查手机号是否存在
     */
    boolean existsByPhone(String phone);
}

