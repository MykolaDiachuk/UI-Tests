package org.example.demo.enums;

public enum TargetLevel {
    NOT_DEFINED("Not defined"),
    NOVICE("Novice"),
    INTERMEDIATE("Intermediate"),
    ADVANCED("Advanced");

    private final String label;

    TargetLevel(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
