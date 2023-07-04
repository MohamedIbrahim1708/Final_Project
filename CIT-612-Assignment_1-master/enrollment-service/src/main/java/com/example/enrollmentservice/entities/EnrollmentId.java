package com.example.enrollmentservice.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EnrollmentId implements Serializable {

    private Long studentEnrolledId;
    private String courseEnrolledId;
}
