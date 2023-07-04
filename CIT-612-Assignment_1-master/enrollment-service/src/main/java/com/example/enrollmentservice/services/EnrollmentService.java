package com.example.enrollmentservice.services;

import com.example.enrollmentservice.entities.Enrollment;

import java.util.List;

public interface EnrollmentService {
    String saveEnrollment(Enrollment enrollment);

    List<Enrollment> fetchEnrollments();

    List<Enrollment> fetchEnrollmentsByStudentId(Long studentEnrolledId);

    String deleteEnrollment(Long studentId, String courseCode);

    Enrollment updateEnrollment(Long studentId, String courseCode, Enrollment enrollment);
}