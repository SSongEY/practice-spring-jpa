package com.example.demo.domain.customer;

import com.example.demo.domain.customer.dto.CustomerDto;
import com.example.demo.domain.customer.entity.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/customers")
    public Page<CustomerDto.ListRes> getCustomers(final Pageable pageable) {
        return customerService.getCustomers(pageable);
    }

    @GetMapping("/customers/{id}")
    public CustomerDto.DetailRes getCustomer(@PathVariable Long id) {
        return customerService.getCustomer(id);
    }

    @PostMapping("/customers")
    public void addCustomer(@RequestBody CustomerDto.CreateReq req) {
        customerService.addCustomer(req);
    }

    @PutMapping("/customers:id")
    public void updateCustomer(@PathVariable Long id, @RequestBody CustomerDto.UpdateReq req) {
        customerService.updateCustomer(id, req);
    }
}
