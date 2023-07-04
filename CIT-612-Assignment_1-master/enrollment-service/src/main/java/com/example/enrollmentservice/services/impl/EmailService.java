package com.example.enrollmentservice.services.impl;

import com.example.enrollmentservice.entities.Enrollment;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Value("${app.kafka.notification-topic-name}")
    private String topicName;

    private KafkaTemplate kafkaTemplate;
    private ObjectMapper objectMapper;

    public EmailService(KafkaTemplate kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public void sendEmail(Enrollment enrollment) {
        try {
            kafkaTemplate.send(topicName, objectMapper.writeValueAsString(enrollment));
            System.out.println("Pushed on the kafka broker");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}
