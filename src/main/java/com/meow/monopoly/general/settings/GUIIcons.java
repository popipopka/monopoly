package com.meow.monopoly.general.settings;

public enum GUIIcons {
    DELETE("anto-delete"),
    CLOSE("anto-close");

    private final String name;

    GUIIcons(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
