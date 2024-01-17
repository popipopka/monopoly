package com.meow.monopoly.gui;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;

public class GUIUtil {
    private GUIUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static double getScreenWidth() {
        return Screen.getPrimary().getBounds().getWidth();
    }

    public static double getScreenHeight() {
        return Screen.getPrimary().getBounds().getHeight();
    }

    public static Rectangle createRect(double a, double b, Color color) {
        return new Rectangle(a, b, color);
    }
}
