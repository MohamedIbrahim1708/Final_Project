package com.example.studentservice.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Student {

    @Id
    private Long studentId;
    private String studentName;
    private String address;
    private String phone;
    private String username;
    private String password;
    private String dept;
}
