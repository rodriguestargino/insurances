package com.audsat.insurances.enums;

import java.util.Arrays;

public enum ProposedCoverage {
    FULL_COVERAGE("Full coverage"),
    LIABILITY_COVERAGE("Liability coverage"),
    COLLISION_COVERAGE("Collision coverage");

    private String label;

    ProposedCoverage(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

}
