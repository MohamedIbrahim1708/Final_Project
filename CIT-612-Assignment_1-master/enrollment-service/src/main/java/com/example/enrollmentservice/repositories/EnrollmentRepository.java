package com.example.enrollmentservice.repositories;

import com.example.enrollmentservice.entities.Enrollment;
import com.example.enrollmentservice.entities.EnrollmentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, EnrollmentId> {

    List<Enrollment> findByEnrollmentIdStudentEnrolledId(Long studentEnrolledId);
}
