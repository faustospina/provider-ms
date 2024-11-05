package com.kafka.provider_ms.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.provider_ms.model.dto.MovimientoSQS;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class MovimientoServiceImpl implements MovimientoService{

    private final KafkaTemplate<String, String> kafkaTemplate;

    private final ObjectMapper objectMapper;



    @Override
    public void addMovimientosToSqs(MovimientoSQS request) throws JsonProcessingException {
        String message = objectMapper.writeValueAsString(request);
        uploadMessage(message);
    }

    private void uploadMessage(String message) {
        kafkaTemplate.send("movimientos-topic", message);
    }
}
