package com.example.demo.domain.customer.dto;

import com.example.demo.domain.customer.entity.Customer;
import com.example.demo.domain.customer.entity.Disabled;
import com.example.demo.domain.customer.entity.Pregnant;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CustomerDtoMapper {
    CustomerDtoMapper INSTANCE = Mappers.getMapper(CustomerDtoMapper.class);

    CustomerDto.ListRes toListResFromCustomer(Customer customer);

    Customer toCustomerFromCreateReq(CustomerDto.CreateReq createReq);

    CustomerDto.DetailRes toDetailResFromCustomer(Customer customer);

    Pregnant toPregnant(CustomerDto.PregnantInfo pregnantInfo, Customer customer);

    Disabled toDisabled(CustomerDto.DisabledInfo disabledInfo, Customer customer);
}
