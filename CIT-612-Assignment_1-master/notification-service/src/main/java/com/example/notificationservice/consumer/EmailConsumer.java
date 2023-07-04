package com.example.notificationservice.consumer;

import com.example.notificationservice.services.EmailSenderService;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Component
public class EmailConsumer {

    @Autowired
    EmailSenderService emailSenderService;

    public static final String ADMIN_EMAIL = "mostafaf743@gmail.com";

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @KafkaListener(
            containerFactory = "emailContainerFactory",
            groupId = "notificationService",
            topics = "${app.kafka-consumers.topic-name}"
    )
    public void getMessagePayload(JSONObject message) {
        logger.info("Adding new enrollment received {}", message);
        JSONObject enrollmentId = (JSONObject) message.get("enrollmentId");
        String studentEnrolledEmail = message.get("studentEnrolEmail").toString();
        emailSenderService.sendSimpleEmail(studentEnrolledEmail,"New Enrollment Request", "New Enrollment Request added: " + "\n" + "Course Code: " + enrollmentId.get("courseEnrolledId").toString() + "\n" + "Student ID: " + enrollmentId.get("studentEnrolledId").toString() +  "\n" + "Enrolled Date: " + message.get("enrolledDate").toString());
    }

}








