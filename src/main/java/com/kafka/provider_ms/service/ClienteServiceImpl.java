package com.kafka.provider_ms.service;


import com.kafka.provider_ms.exception.NotFoundException;
import com.kafka.provider_ms.model.dto.ClienteDTO;
import com.kafka.provider_ms.model.entities.Cliente;
import com.kafka.provider_ms.model.mapper.ClienteMapper;
import com.kafka.provider_ms.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class ClienteServiceImpl implements ClienteService{

    @Autowired
    private ClienteRepository repository;


    @Autowired
    private ClienteMapper mapper;


    @Override
    public ResponseEntity<Object> getAllClientes() {
        return new ResponseEntity<>(repository
                .findAll()
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> createCliente(ClienteDTO dto) {
        Cliente cliente = mapper.toEntity(dto);
        return new ResponseEntity<>(mapper.toDTO(repository.save(cliente)),HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Object> deleteCliente(Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Object> updateCliente(Long id,ClienteDTO dto) {
        Cliente cliente = repository.findById(id).orElseThrow(()->new NotFoundException("not found client"));
        cliente.setContrasena(dto.getContrasena());
        cliente.setEstado(dto.getEstado());
        cliente.getPersona().setNombre(dto.getPersona().getNombre());
        cliente.getPersona().setGenero(dto.getPersona().getGenero());
        cliente.getPersona().setEdad(dto.getPersona().getEdad());
        cliente.getPersona().setIdentificacion(dto.getPersona().getIdentificacion());
        cliente.getPersona().setDireccion(dto.getPersona().getDireccion());
        cliente.getPersona().setTelefono(dto.getPersona().getTelefono());
        return new ResponseEntity<>(mapper.toDTO(repository.save(cliente)),HttpStatus.OK);
    }
}
