package com.kafka.provider_ms.service;

import com.kafka.provider_ms.model.dto.ClienteDTO;
import org.springframework.http.ResponseEntity;

public interface ClienteService {

    ResponseEntity<Object> getAllClientes();
    ResponseEntity<Object> createCliente(ClienteDTO dto);

    ResponseEntity<Object> deleteCliente(Long id);

    ResponseEntity<Object> updateCliente(Long id,ClienteDTO dto);

}
