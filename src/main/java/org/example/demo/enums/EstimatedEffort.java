package org.example.demo.enums;

public enum EstimatedEffort {
    UP_TO_1_HOUR("Up to 1 hour"),
    ONE_TO_FOUR_HOURS("1-4 hours"),
    FOUR_TO_EIGHT_HOURS("4-8 hours"),
    EIGHT_TO_TWENTY_HOURS("8-20 hours"),
    MORE_THAN_TWENTY_HOURS("More than 20 hours");

    private final String label;

    EstimatedEffort(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
