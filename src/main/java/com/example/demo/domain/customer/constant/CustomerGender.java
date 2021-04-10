package com.example.demo.domain.customer.constant;

public enum CustomerGender {
    MAN("남자"),
    WOMAN("여자")
    ;

    private String name;
    CustomerGender(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
