package org.example.accountservice.constants;

import jakarta.persistence.EnumType;
import jakarta.persistence.EnumeratedValue;

public enum AccountType {

    CONSUMER("Consumer"),
    BUSINESS("Business");

    @EnumeratedValue
    private final String name;

    AccountType(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

}
