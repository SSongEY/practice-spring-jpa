package com.example.demo.domain.customer.entity;

import com.example.demo.domain.customer.constant.CustomerType;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("Pregnant")
public class Pregnant extends MobilityWeakInfo {

    private LocalDate babyDue;

    @Builder
    public Pregnant(Customer customer, LocalDate babyDue) {
        super(customer);
        this.babyDue = babyDue;
    }
}
