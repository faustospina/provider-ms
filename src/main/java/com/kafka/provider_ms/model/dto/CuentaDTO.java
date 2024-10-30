package com.kafka.provider_ms.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CuentaDTO {

    private String numeroCuenta;

    private String tipoCuenta;
    private double saldoInicial;
    private Boolean estado;

    private List<MovimientoDTO> movimientos;

    private ClienteDTO cliente;
}