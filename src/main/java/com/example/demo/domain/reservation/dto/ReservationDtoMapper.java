package com.example.demo.domain.reservation.dto;

import com.example.demo.domain.customer.entity.Customer;
import com.example.demo.domain.reservation.entity.ReservationPassenger;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ReservationDtoMapper {
    ReservationDtoMapper INSTANCE = Mappers.getMapper(ReservationDtoMapper.class);

    ReservationPassenger toReservationPassengerEntity(ReservationDto.CreateReq dto);
}
