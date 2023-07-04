package com.example.studentservice.services.impl;

import com.example.studentservice.entities.Student;
import com.example.studentservice.repositories.StudentRepository;
import com.example.studentservice.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;
    // Create New Student
    @Override
    public String saveStudent(Student student) {
        if (isStudentExists(student.getStudentId())){
            return "This Student is already exists";
        }
        studentRepository.save(student);
        return "Student is added successfully";
    }
    // Get All Student
    @Override
    public List<Student> fetchStudents() {
        return studentRepository.findAll();
    }
    // Get Student By ID
    @Override
    public Student getStudentById(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            return student.get();
        } else {
            throw new RuntimeException("Student not found with id: " + id);
        }
    }
    // Update Student
    @Override
    public Student updateStudent(Long id, Student student) {
        Optional<Student> existingStudent = studentRepository.findById(id);
        if (existingStudent.isPresent()) {
            Student updatedStudent = existingStudent.get();
            updatedStudent.setStudentName(student.getStudentName());
            updatedStudent.setAddress(student.getAddress());
            updatedStudent.setUsername(student.getUsername());
            updatedStudent.setPhone(student.getPhone());
            updatedStudent.setPassword(student.getPassword());
            updatedStudent.setDept(student.getDept());
            return studentRepository.save(updatedStudent);
        } else {
            throw new RuntimeException("Student not found with id: " + id);
        }
    }
    // Delete Student
    @Override
    public void deleteStudent(Long id) {
        Optional<Student> existingStudent = studentRepository.findById(id);
        if (existingStudent.isPresent()) {
            studentRepository.delete(existingStudent.get());
        } else {
            throw new RuntimeException("Student not found with id: " + id);
        }
    }

    public boolean isStudentExists(Long studentId){
        return studentRepository.existsById(studentId);
    }
}