package com.meow.monopoly.gui.mainpanel;

import com.meow.monopoly.game.Player;
import com.meow.monopoly.gui.PlayerIcons;
import org.kordamp.ikonli.javafx.FontIcon;

import static com.meow.monopoly.general.settings.GUISizes.PLAYER_ICON;

public class PlayerGUI extends FontIcon {
    private final Player player;

    public PlayerGUI(Player player) {
        this.player = player;
        PlayerIcons icon = PlayerIcons.values()[this.player.getId()];

        this.getStyleClass().add("player");

        this.setIconLiteral(icon.getName());
        this.iconSize.set(PLAYER_ICON.get());
        this.iconColor.set(icon.getColor());
    }

    public int getPosition() {
        return this.player.getPosition();
    }
}
