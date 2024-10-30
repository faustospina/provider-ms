package com.kafka.provider_ms.model.mapper;

import com.kafka.provider_ms.model.dto.MovimientoDTO;
import com.kafka.provider_ms.model.entities.Movimiento;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MovimientoMapper {
    MovimientoDTO toDTO(Movimiento entity);
    Movimiento toEntity(MovimientoMapper dto);
}
