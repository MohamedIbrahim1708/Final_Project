package com.example.enrollmentservice.services.impl;

import com.example.courseservice.services.impl.CourseServiceImpl;
import com.example.enrollmentservice.entities.Enrollment;
import com.example.enrollmentservice.entities.EnrollmentId;
import com.example.enrollmentservice.repositories.EnrollmentRepository;
import com.example.enrollmentservice.services.EnrollmentService;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import com.example.courseservice.services.impl.CourseServiceImpl;
import com.example.studentservice.services.impl.StudentServiceImpl;

@RequiredArgsConstructor
@Service
@Configuration
public class EnrollmentServiceImpl implements EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    private final EmailService emailService;

    public static final LocalDate ADD_DROP_FINAL_DATE = LocalDate.now();
    public static final int ADD_DROP_DURATION_DAYS = 14;


    @Override
    public String saveEnrollment(Enrollment enrollment) {
        if (isEnrollmentExists(enrollment.getEnrollmentId())){
            return "This Enrollment is already exists";
        }
        if (enrollment.getEnrolledDate() == null){
            enrollment.setEnrolledDate(LocalDate.now());
        }
        if (enrollment.getEnrolledDate().compareTo(ADD_DROP_FINAL_DATE) > 0){
            return "Add/Drop courses duration is finished, you can not add it now";
        }
        enrollmentRepository.save(enrollment);
        emailService.sendEmail(enrollment);
        return "Enrollment is added successfully";
    }

    @Override
    public List<Enrollment> fetchEnrollments() {
        return enrollmentRepository.findAll();
    }

    @Override
    public List<Enrollment> fetchEnrollmentsByStudentId(Long studentEnrolledId) {
        return enrollmentRepository.findByEnrollmentIdStudentEnrolledId(studentEnrolledId);
    }

    @Override
    public String deleteEnrollment(Long studentId, String courseCode) {
        EnrollmentId enrollmentId = new EnrollmentId(studentId, courseCode);
        if (!isEnrollmentExists(enrollmentId)){
            return "This Enrollment is not existing";
        }
        Period period = Period.between(enrollmentRepository.findById(enrollmentId).get().getEnrolledDate() ,ADD_DROP_FINAL_DATE);
        if (period.getDays() > ADD_DROP_DURATION_DAYS) {
            return "Add/Drop courses duration is finished, you can not drop it now";
        }
        enrollmentRepository.deleteById(enrollmentId);
        return "Enrollment Deleted successfully";
    }

    @Override
    public Enrollment updateEnrollment(Long studentId, String courseCode, Enrollment enrollment) {
        Enrollment enrollmentDB = enrollmentRepository.findById(new EnrollmentId(studentId, courseCode)).get();
        enrollmentDB.setEnrolledDate(enrollment.getEnrolledDate());

        return enrollmentRepository.save(enrollmentDB);
    }

    public boolean isEnrollmentExists(EnrollmentId enrollmentId){
        return enrollmentRepository.existsById(enrollmentId);
    }

}