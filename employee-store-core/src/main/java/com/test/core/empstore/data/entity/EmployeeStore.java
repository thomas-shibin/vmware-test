package com.test.core.empstore.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by shibthom on 1/23/2021.
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_EMPLOYEE")
public class EmployeeStore implements Serializable{

    private static final long serialVersionUID = -161276651943710341L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;

    @Column(name="NAME")
    private String name;

    @Column(name = "AGE")
    private Integer age = 0;

    @Column(name = "FILE_TIMESTAMP", nullable = false, updatable=false)
    private LocalDateTime fileTimestamp;

    @CreationTimestamp
    @Column(name = "CREATED_TIME", nullable = false, updatable=false)
    private LocalDateTime createdTime;
}
