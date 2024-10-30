package com.kafka.provider_ms.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kafka.provider_ms.model.dto.MovimientoSQS;
import com.kafka.provider_ms.service.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("movimientos")
public class MovimientoController {

    @Autowired
    private MovimientoService service;

    @PostMapping
    public void addMovimiento(@RequestBody MovimientoSQS request) throws JsonProcessingException {
        service.addMovimientosToSqs(request);
    }

}
