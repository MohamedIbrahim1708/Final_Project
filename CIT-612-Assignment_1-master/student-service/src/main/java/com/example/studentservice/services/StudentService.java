package com.example.studentservice.services;

import com.example.studentservice.entities.Student;

import java.util.List;

public interface StudentService {
    // Create New Student
    String saveStudent(Student student);
    // Get All Student
    List<Student> fetchStudents();
    // Get Student By ID
    Student getStudentById(Long id);
    // Update Student
    Student updateStudent(Long id, Student student);
    // Delete Student
    void deleteStudent(Long id);
}
