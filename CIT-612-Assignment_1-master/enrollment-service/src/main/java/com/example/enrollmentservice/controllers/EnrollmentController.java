package com.example.enrollmentservice.controllers;

import com.example.enrollmentservice.entities.Enrollment;
import com.example.enrollmentservice.services.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    @PostMapping("/api/enrollments")
    public String saveEnrollment(@RequestBody Enrollment enrollment){
        return enrollmentService.saveEnrollment(enrollment);
    }

    @GetMapping("/api/enrollments")
    public List<Enrollment> fetchEnrollments(){
        return enrollmentService.fetchEnrollments();
    }

    @GetMapping("/api/enrollments/{id}")
    public List<Enrollment> fetchEnrollmentsByStudentId(@PathVariable("id") Long studentEnrolledId){
        return enrollmentService.fetchEnrollmentsByStudentId(studentEnrolledId);
    }

    @DeleteMapping("/api/enrollments/{studentId}/{courseCode}")
    public String deleteEnrollment(@PathVariable("studentId") Long studentId, @PathVariable("courseCode") String courseCode){
        return enrollmentService.deleteEnrollment(studentId, courseCode);
    }

    @PutMapping("/api/enrollments/{studentId}/{courseCode}")
    public Enrollment updateEnrollment(@PathVariable("studentId") Long studentId, @PathVariable("courseCode") String courseCode, @RequestBody Enrollment enrollment){
        return enrollmentService.updateEnrollment(studentId, courseCode, enrollment);
    }
}
