package com.example.studentservice.controllers;

import com.example.studentservice.entities.Student;
import com.example.studentservice.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;

    // Insert New Student
    @PostMapping("/api/students/")
    public String saveStudent(@RequestBody Student student){
        return studentService.saveStudent(student);
    }
    // Get all Student
    @GetMapping("/api/students/")
    public List<Student> fetchStudents(){
        return studentService.fetchStudents();
    }
    // Get Student By ID
    @GetMapping("/api/students/{id}")
    public Student getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }
    // Update Student
    @PutMapping("/api/students/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody Student student) {
        return studentService.updateStudent(id, student);
    }
    // Delete Student
    @DeleteMapping("/api/students/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }
}