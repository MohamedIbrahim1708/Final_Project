package com.example.courseservice.services.impl;

import com.example.courseservice.entities.Course;
import com.example.courseservice.repositories.CourseRepository;
import com.example.courseservice.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseRepository courseRepository;

    @Override
    public Course getCourseByCode(String code) {

        Optional<Course> course = courseRepository.findById(code);

        if (course.isPresent()) {
            return course.get();
        } else {
            return null;
        }
    }
    public boolean isDataIdExists(String courseCode) {
        // Check if data ID exists in the database
        return courseRepository.existsById(courseCode);
    }

    @Override
    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public List<Course> getAllCourse() {
        return courseRepository.findAll();
    }

    @Override
    public Course updateCourseByCode(String code, Course updatedCourse) {

        Optional<Course> course = courseRepository.findById(code);

        if (course.isPresent()) {
            course.get().setCourseCode(updatedCourse.getCourseCode());
            course.get().setCourseName(updatedCourse.getCourseName());
            course.get().setCourseCredits(updatedCourse.getCourseCredits());
            course.get().setCourseCapacity(updatedCourse.getCourseCapacity());
            course.get().setUsedCapacity(updatedCourse.getUsedCapacity());

            return courseRepository.save(course.get());
        } else {
            return null;
        }
    }

    @Override
    public boolean deleteCourseByCode(String code) {

        Optional<Course> course = courseRepository.findById(code);

        if (course.isPresent()) {
            courseRepository.deleteById(code);
            return true;
        } else {
            return false;
        }
    }
}
