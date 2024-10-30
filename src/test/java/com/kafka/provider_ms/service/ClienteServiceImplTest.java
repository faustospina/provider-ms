package com.kafka.provider_ms.service;

import com.kafka.provider_ms.exception.NotFoundException;
import com.kafka.provider_ms.model.dto.ClienteDTO;
import com.kafka.provider_ms.model.dto.PersonaDTO;
import com.kafka.provider_ms.model.entities.Cliente;
import com.kafka.provider_ms.model.entities.Persona;
import com.kafka.provider_ms.model.mapper.ClienteMapper;
import com.kafka.provider_ms.repository.ClienteRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class ClienteServiceImplTest {

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private ClienteMapper clienteMapper;

    @InjectMocks
    private ClienteServiceImpl clienteService;

    private Cliente cliente;
    private ClienteDTO clienteDTO;

    @BeforeEach
    void setUp() {
        // Inicializa objetos de prueba
        Persona persona = new Persona(1L, "Juan", "M", 30, "12345", "Calle 10", "555-1234");
        cliente = new Cliente(1L, "password123", true, persona);

        PersonaDTO personaDTO = new PersonaDTO("Juan", "M", 30, "12345", "Calle 10", "555-1234");
        clienteDTO = new ClienteDTO(1L, "password123", true, personaDTO);
    }

    @Test
    void testGetAllClientes() {
        when(clienteRepository.findAll()).thenReturn(List.of(cliente));
        when(clienteMapper.toDTO(cliente)).thenReturn(clienteDTO);

        ResponseEntity<Object> response = clienteService.getAllClientes();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        List<ClienteDTO> clientes = (List<ClienteDTO>) response.getBody();
        assertNotNull(clientes);
        assertEquals(1, clientes.size());
        assertEquals("Juan", clientes.get(0).getPersona().getNombre());
    }

    @Test
    void testCreateCliente() {
        when(clienteMapper.toEntity(clienteDTO)).thenReturn(cliente);
        when(clienteRepository.save(cliente)).thenReturn(cliente);
        when(clienteMapper.toDTO(cliente)).thenReturn(clienteDTO);

        ResponseEntity<Object> response = clienteService.createCliente(clienteDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        ClienteDTO result = (ClienteDTO) response.getBody();
        assertNotNull(result);
        assertEquals("Juan", result.getPersona().getNombre());
    }

    @Test
    void testDeleteCliente() {
        doNothing().when(clienteRepository).deleteById(1L);

        ResponseEntity<Object> response = clienteService.deleteCliente(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void testUpdateClienteNotFound() {
        when(clienteRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> {
            clienteService.updateCliente(1L, clienteDTO);
        });
    }
}