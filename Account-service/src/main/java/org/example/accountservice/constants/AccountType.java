package org.example.accountservice.constants;

public enum AccountType {

    CONSUMER("Consumer"),
    BUSINESS("Business");

    private final String name;

    AccountType(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

}
