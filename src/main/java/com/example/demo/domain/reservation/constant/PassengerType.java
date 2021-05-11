package com.example.demo.domain.reservation.constant;

public enum PassengerType {

    BOOKER("예약자"),
    FELLOW("동승자")
    ;

    private String name;

    PassengerType(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
