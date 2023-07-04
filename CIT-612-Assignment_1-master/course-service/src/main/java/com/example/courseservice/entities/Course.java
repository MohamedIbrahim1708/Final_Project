package com.example.courseservice.entities;

import lombok.*;

import javax.persistence.*;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Data
@Table(name = "COURSE")
public class Course {

    @Id
    @Column(name = "courseCode")
    private String courseCode;
    @Column(name = "courseName")
    private String courseName;
    @Column(name = "courseCredits")
    private int courseCredits;
    // Other course properties, getters, and setters
    @Column(name = "courseCapacity")
    private Long courseCapacity;
    @Column(name = "usedCapacity")
    private Long usedCapacity;


}


