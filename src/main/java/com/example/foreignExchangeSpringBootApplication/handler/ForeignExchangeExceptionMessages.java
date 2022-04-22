package com.example.foreignExchangeSpringBootApplication.handler;

public enum ForeignExchangeExceptionMessages {

    EXTERNAL_API_CONNECTION_ERROR("An error occurred while connecting External Api for retrieve currency information."),
    UNKNOWN_EXCEPTION_ERROR("Unknown error occurred: "),
    GET_CONVERSION_LIST_ERROR("An error occurred while retrieve conversion information: "),
    SAVE_CONVERSION_TO_DB_ERROR("An error occurred while save information to database: ");

    ForeignExchangeExceptionMessages(String description) {
        this.description = description;
    }

    private final String description;

    public String getDescription() {
        return description;
    }
}