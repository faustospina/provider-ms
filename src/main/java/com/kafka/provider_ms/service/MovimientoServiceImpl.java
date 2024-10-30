package com.kafka.provider_ms.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.provider_ms.model.dto.MovimientoSQS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class MovimientoServiceImpl implements MovimientoService{
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    @Autowired
    private  ObjectMapper objectMapper;



    @Override
    public void addMovimientosToSqs(MovimientoSQS request) throws JsonProcessingException {
        String message = objectMapper.writeValueAsString(request);
        uploadMessage(message);
    }

    private void uploadMessage(String message) {
        kafkaTemplate.send("movimientos-topic", message);
    }
}
