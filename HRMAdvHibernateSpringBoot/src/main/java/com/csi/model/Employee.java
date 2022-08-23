package com.csi.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue
    private int empId;

    private String empName;

    private String empAddress;

    private double empSalary;

    private int empAge;

    @Column(name = "empContactNumber" , unique = true)
    private long empContactNumber;

    @JsonFormat(pattern = "dd-MM-yyyy" , timezone = "Asia/Kolkata")
    private Date empDOB;

    @Column(name = "empEmailId" , unique = true)
    private String empEmailId;

    private String empPassword;


}
