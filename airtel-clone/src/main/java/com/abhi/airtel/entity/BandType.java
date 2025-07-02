package com.abhi.airtel.entity;

public enum BandType {
    TWO_FOUR_GHZ("2.4_GHZ"),
    FIVE_GHZ("5_GHZ"),
    GUEST_TWO_FOUR_GHZ("GUEST_2.4_GHZ"),
    GUEST_FIVE_GHZ("GUEST_5_GHZ");

    private final String label;

    BandType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}