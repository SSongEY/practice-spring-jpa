package com.example.demo.domain.coupon.entity;

import com.example.demo.domain.customer.entity.Customer;

import javax.persistence.*;

@Entity
public class CustomerCoupon {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "coupon_id")
    private Coupon coupon;
}
