package com.kafka.provider_ms.model.mapper;

import com.kafka.provider_ms.model.dto.CuentaDTO;
import com.kafka.provider_ms.model.entities.Cuenta;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CuentaMapper {
    CuentaDTO toDTO(Cuenta entity);
    Cuenta toEntity(CuentaDTO dto);
}
