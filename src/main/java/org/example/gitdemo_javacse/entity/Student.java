package org.example.gitdemo_javacse.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * 学生实体类
 */
@Entity
@Table(name = "student")
public class Student implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "student_number", unique = true, nullable = false, length = 50)
    private String studentNumber; // 学号
    
    @Column(name = "name", nullable = false, length = 50)
    private String name; // 姓名
    
    @Column(name = "age", nullable = false)
    private Integer age; // 年龄
    
    @Column(name = "phone", unique = true, nullable = false, length = 20)
    private String phone; // 手机号
    
    @Column(name = "enrollment_date", nullable = false)
    private LocalDate enrollmentDate; // 入学时间
    
    @Column(name = "major_id")
    private Long majorId; // 专业ID（外键，暂时用Long，后续会关联Major实体）
    
    public Student() {
    }
    
    public Student(String studentNumber, String name, Integer age, String phone, LocalDate enrollmentDate, Long majorId) {
        this.studentNumber = studentNumber;
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.enrollmentDate = enrollmentDate;
        this.majorId = majorId;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getStudentNumber() {
        return studentNumber;
    }
    
    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Integer getAge() {
        return age;
    }
    
    public void setAge(Integer age) {
        this.age = age;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }
    
    public void setEnrollmentDate(LocalDate enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }
    
    public Long getMajorId() {
        return majorId;
    }
    
    public void setMajorId(Long majorId) {
        this.majorId = majorId;
    }
}

