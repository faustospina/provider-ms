package com.kafka.provider_ms.model.dto;

public record MovimientoSQS(String nomeroCuenta, TipoMovimiento tipoMovimiento, double valor) {
}
