package com.meow.monopoly.gui.subpanel;

import com.meow.monopoly.game.Cell;
import com.meow.monopoly.game.util.CellUtil;
import com.meow.monopoly.general.Updated;
import com.meow.monopoly.general.settings.GUIIcons;
import com.meow.monopoly.gui.GUIUtil;
import com.meow.monopoly.gui.PlayerIcons;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import org.kordamp.ikonli.javafx.FontIcon;

import static com.meow.monopoly.general.settings.GUISizes.CELL_AVATAR_RECT_SIZE;
import static com.meow.monopoly.general.settings.GUISizes.INFO_BOARD_WIDTH;

public class CellAvatar extends HBox implements Updated {
    private final Cell cell;
    private FontIcon ownerIcon;
    private Label levelValue;

    // TODO изменяемую цену и ренту в зависимости от уровня, отображение текущего уровня (домиков)
    public CellAvatar(Cell cell) {
        this.cell = cell;

        this.getStyleClass().add("cell-avatar");

        Rectangle coloredRect = GUIUtil.createRect(
                CELL_AVATAR_RECT_SIZE.get(),
                CELL_AVATAR_RECT_SIZE.get(),
                cell.getColor()
        );

        this.getChildren().addAll(coloredRect, createLabels(), createIcon());
    }

    private VBox createLabels() {
        String labelStyle = "underline";
        VBox box = new VBox();
        box.setMinSize(INFO_BOARD_WIDTH.get() * 0.4, CELL_AVATAR_RECT_SIZE.get());

        Label nameValue = new Label(cell.getName());
        nameValue.getStyleClass().add(labelStyle);

        box.getChildren().add(nameValue);

        if (!CellUtil.isObject(this.cell)) return box;

        Label cost = new Label("Цена");
        Label rent = new Label("Рента");
        Label level = new Label("Дома");
        Label upgradeCost = new Label("Цена улучшения");

        cost.getStyleClass().add(labelStyle);
        rent.getStyleClass().add(labelStyle);
        level.getStyleClass().add(labelStyle);
        upgradeCost.getStyleClass().add(labelStyle);

        Label costValue = new Label(": " + cell.getCost());
        Label rentValue = new Label(": " + cell.getRent());
        this.levelValue = new Label(": " + cell.getLevel());
        Label upgradeCostValue = new Label(": " + cell.getUpgradeCost());

        box.getChildren().addAll(
                new HBox(cost, costValue),
                new HBox(rent, rentValue),
                new HBox(level, levelValue),
                new HBox(upgradeCost, upgradeCostValue)
        );

        return box;
    }

    private HBox createIcon() {
        int size = (int) (CELL_AVATAR_RECT_SIZE.get() * 0.5);

        HBox box = new HBox();
        box.setMinSize(size, size);
        box.setMaxSize(size, size);

        if (!CellUtil.isObject(cell)) return box;

        ownerIcon = new FontIcon(GUIIcons.CLOSE.getName());
        ownerIcon.setIconSize(size);
        ownerIcon.getStyleClass().add("avatar-icon");

        box.getStyleClass().add("player-avatar");
        box.getChildren().add(ownerIcon);

        return box;
    }

    private void setOwnerIcon() {
        PlayerIcons iconInfo = PlayerIcons.values()[this.cell.getOwner().getId()];
        ownerIcon.setIconLiteral(iconInfo.getName());
        ownerIcon.setIconColor(iconInfo.getColor());
    }

    @Override
    public void update() {
        if (this.cell.isOwned()) setOwnerIcon();
        if(CellUtil.isObject(this.cell)) this.levelValue.setText(": " + this.cell.getLevel());
    }
}
