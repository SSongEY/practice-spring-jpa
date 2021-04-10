package com.example.demo.domain.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
@Getter
@AllArgsConstructor @Builder
public class Address {
    private String city;
    private String street;
    private String zipcode;
}
