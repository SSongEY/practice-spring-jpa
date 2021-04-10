package com.example.demo.domain.customer.constant;

public enum CustomerType {
    NORMAL("일반"),
    PREGNANT("임산부"),
    DISABLED("장애인")
    ;

    private String name;
    CustomerType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
