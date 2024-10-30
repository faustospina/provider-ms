package com.kafka.provider_ms.controller;

import com.kafka.provider_ms.model.dto.ClienteDTO;
import com.kafka.provider_ms.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("clientes")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @PostMapping
    public ResponseEntity<Object> createCliente(@RequestBody ClienteDTO request){
        return service.createCliente(request);
    }
    @GetMapping
    public ResponseEntity<Object> getAll(){
        return service.getAllClientes();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCliente(@PathVariable Long id, @RequestBody ClienteDTO request){
        return service.updateCliente(id,request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCliente(@PathVariable Long id){
        service.deleteCliente(id);
        return ResponseEntity.noContent().build();
    }

}
