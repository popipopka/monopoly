package com.meow.monopoly.gui;

import javafx.scene.paint.Color;

public enum PlayerIcons {
    APPLE("antf-apple", Color.GRAY),
    ANDROID("antf-android", Color.GREEN),
    CAR("antf-car", Color.RED),
    CROWN("antf-crown", Color.GOLDENROD);

    private final String name;
    private final Color color;

    PlayerIcons(String name, Color color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }
}
