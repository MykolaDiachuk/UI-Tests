package org.example.demo.enums;

public enum Language {
    ENGLISH("English"),
    ARABIC("Arabic"),
    ARMENIAN("Armenian"),
    BASQUE("Basque"),
    CZECH("Czech"),
    SPANISH("Spanish"),
    FRENCH("French"),
    BELARUSIAN("Belarusian"),
    HEBREW("Hebrew");

    private final String label;

    Language(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
