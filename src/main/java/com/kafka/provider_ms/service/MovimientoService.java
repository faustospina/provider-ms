package com.kafka.provider_ms.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kafka.provider_ms.model.dto.MovimientoSQS;

public interface MovimientoService {
    void addMovimientosToSqs(MovimientoSQS request) throws JsonProcessingException;
}
