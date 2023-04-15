package com.collidacube.prison.prisoncore.data;

public record PrisonEconomyResponse(double amount, double balance, ResponseType type, String errorMessage) {

    public enum ResponseType {
        SUCCESS,
        FAILURE,
        NOT_IMPLEMENTED
    }

    public boolean transactionSuccess() {
        return type == ResponseType.SUCCESS;
    }
}