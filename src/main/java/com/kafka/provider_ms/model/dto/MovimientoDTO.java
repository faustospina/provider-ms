package com.kafka.provider_ms.model.dto;

import com.kafka.provider_ms.model.entities.Cuenta;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovimientoDTO {
    private LocalDate fecha;
    private String tipoMovimiento;
    private double valor;
    private double saldo;

    private Cuenta cuenta;
}
