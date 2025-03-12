package org.example.demo.enums;

public enum Skill {
    AT_JAVA("AT/Java"),
    JAVA_CORE("Java Core"),
    AI_IN_TEST_AUTOMATION("AI in Test Automation");

    private final String label;

    Skill(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
