package com.example.courseservice.services;


import com.example.courseservice.entities.Course;

import java.util.List;

public interface CourseService {

    public Course getCourseByCode(String code);

    public Course saveCourse(Course course);

    public List<Course> getAllCourse();

    public Course updateCourseByCode(String code, Course updatedCourse);

    public boolean deleteCourseByCode(String code);


}
