package com.example.demo.domain.customer.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("Disabled")
public class Disabled extends MobilityWeakInfo {

    private Boolean isCommunicable;
    private String handicapInfo;


    public Disabled(Customer customer, Boolean isCommunicable, String handicapInfo) {
        super(customer);
        this.isCommunicable = isCommunicable;
        this.handicapInfo = handicapInfo;
    }
}
