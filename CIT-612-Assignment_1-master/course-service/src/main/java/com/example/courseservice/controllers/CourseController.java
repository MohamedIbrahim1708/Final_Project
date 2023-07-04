package com.example.courseservice.controllers;


import com.example.courseservice.entities.Course;
import com.example.courseservice.services.impl.CourseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseController {

    @Autowired
    CourseServiceImpl courseServiceOperation;

    @PostMapping("/api/courses")
    public ResponseEntity<Course> saveReqCourse(@RequestBody Course course) {
        if (courseServiceOperation.isDataIdExists(course.getCourseCode()))
            return new ResponseEntity<>(HttpStatus.CONFLICT);

        return new ResponseEntity<>(courseServiceOperation.saveCourse(course), HttpStatus.CREATED);
    }

    @GetMapping("/api/courses")
    public ResponseEntity<List<Course>> getAllReqCourses() {
        return new ResponseEntity<>(courseServiceOperation.getAllCourse(), HttpStatus.OK);
    }

    @GetMapping("/api/courses/{code}")
    public ResponseEntity<Course> getReqCourseByCode(@PathVariable String code) {


        if (courseServiceOperation.getCourseByCode(code) != null) {
            return new ResponseEntity<>(courseServiceOperation.getCourseByCode(code), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/api/courses/{code}")
    public ResponseEntity<Course> updateReqCourseByCode(@PathVariable String code, @RequestBody Course updatedCourse) {

        if (courseServiceOperation.updateCourseByCode(code, updatedCourse) != null) {
            return new ResponseEntity<>(courseServiceOperation.updateCourseByCode(code, updatedCourse), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //
    @DeleteMapping("/api/courses/{code}")
    public ResponseEntity<Void> deleteReqCourseByCode(@PathVariable String code) {

        if (courseServiceOperation.deleteCourseByCode(code)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
