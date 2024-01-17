package com.meow.monopoly.gui.mainpanel;

import com.meow.monopoly.game.Cell;
import com.meow.monopoly.gui.GUIUtil;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

public class CellGUI extends VBox {
    private final FlowPane playersPane = new FlowPane();

    public CellGUI(double width, double height, Cell cell) {
        this.getStyleClass().add("cell");

        Rectangle coloredRect = GUIUtil.createRect(width, height * 0.1, cell.getColor());
        Label nameLabel = new Label(cell.getName());
        VBox infoPane = new VBox(nameLabel, this.playersPane);

        this.getChildren().addAll(coloredRect, infoPane);

        this.setPrefSize(width, height);
        this.setMaxSize(width, height);
    }

    public void addPlayer(PlayerGUI player) {
        if (!this.playersPane.getChildren().contains(player))
            this.playersPane.getChildren().add(player);
    }
}
