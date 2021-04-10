package com.example.demo.domain.customer;

import com.example.demo.domain.customer.constant.CustomerType;
import com.example.demo.domain.customer.dto.CustomerDto;
import com.example.demo.domain.customer.dto.CustomerDtoMapper;
import com.example.demo.domain.customer.entity.Customer;
import com.example.demo.domain.customer.entity.Disabled;
import com.example.demo.domain.customer.entity.MobilityWeakInfo;
import com.example.demo.domain.customer.entity.Pregnant;
import com.example.demo.domain.customer.exception.NotFoundCustomerException;
import com.example.demo.domain.customer.repo.CustomerRepository;
import com.example.demo.domain.customer.repo.MobilityWeakInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final MobilityWeakInfoRepository mobilityWeakInfoRepository;

    public Page<CustomerDto.ListRes> getCustomers(final Pageable pageable) {
        return customerRepository.findAll(pageable)
                .map(customer -> CustomerDto.ListRes.of(customer))
                ;
    }
    
    public void addCustomer(CustomerDto.CreateReq req) {
        Customer newCustomer = customerRepository.save(req.toEntity());

        CustomerType customerType = CustomerType.valueOf(req.getType());
        if(CustomerType.NORMAL != customerType) {
            MobilityWeakInfo mobilityWeakInfo;
            if(CustomerType.PREGNANT == customerType) {
                mobilityWeakInfo = req.getPregnantInfo().toEntity(newCustomer);
            } else if(CustomerType.DISABLED == customerType) {
                mobilityWeakInfo = req.getDisabledInfo().toEntity(newCustomer);
            } else {
                throw new IllegalArgumentException();
            }
            mobilityWeakInfoRepository.save(mobilityWeakInfo);
        }
    }

    public void updateCustomer(Long id, CustomerDto.UpdateReq req) {
        Customer targetCustomer = customerRepository.findById(id).orElseThrow(NotFoundCustomerException::new);
        targetCustomer.updateInfo(req);
        customerRepository.save(targetCustomer);
    }


    public CustomerDto.DetailRes getCustomer(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(NotFoundCustomerException::new);
        return CustomerDto.DetailRes.of(customer);
    }
}
