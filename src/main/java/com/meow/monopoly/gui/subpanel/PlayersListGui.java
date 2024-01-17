package com.meow.monopoly.gui.subpanel;

import com.meow.monopoly.game.Player;
import com.meow.monopoly.general.Updated;
import com.meow.monopoly.gui.PlayerIcons;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import org.kordamp.ikonli.javafx.FontIcon;

import java.util.HashMap;
import java.util.Map;

import static com.meow.monopoly.general.settings.GUISizes.*;

public class PlayersListGui extends FlowPane implements Updated {
    private final Map<Integer, PlayerListItem> items = new HashMap<>();

    public PlayersListGui(Map<Integer, Player> players) {
        this.getStyleClass().add("players-list");

        PlayerListItem item;
        for (Player p : players.values()) {
            item = new PlayerListItem(p);

            this.getChildren().add(item);
            this.items.put(p.getId(), item);
        }
    }

    /**
     * Выделяет игрока
     * @param playerId id игрока
     */
    public void select(int playerId) {
        this.items.get(playerId).highlight();
    }

    /**
     * Снимает выделение со всех игроков
     */
    public void unselectAll() {
        for (PlayerListItem e : items.values()) {
            e.clearHighlight();
        }
    }

    @Override
    public void update() {
        for (PlayerListItem e : items.values()) {
            e.update();
        }
    }

    static class PlayerListItem extends HBox implements Updated {
        private final Player player;
        private final Label lMoney;

        public PlayerListItem(Player player) {
            this.player = player;
            this.lMoney = new Label(String.valueOf(player.getMoney()));

            this.getStyleClass().add("player-item");
            this.setOpacity(0.5);

            this.setMinSize(PLAYER_LIST_ITEM_WIDTH.get(), PLAYER_LIST_ITEM_HEIGHT.get());
            this.setMaxSize(PLAYER_LIST_ITEM_WIDTH.get(), PLAYER_LIST_ITEM_HEIGHT.get());

            this.getChildren().addAll(createIcon(), createPlayerFieldsInfo());
        }

        public void highlight() {
            this.getStyleClass().add("highlight-player-item");
            this.setOpacity(1);
        }

        public void clearHighlight() {
            this.getStyleClass().clear();
            this.getStyleClass().add("player-item");
            this.setOpacity(0.5);
        }

        private VBox createIcon() {
            PlayerIcons enumIcon = PlayerIcons.values()[player.getId()];

            FontIcon icon = new FontIcon(enumIcon.getName());
            icon.setIconColor(enumIcon.getColor());
            icon.setIconSize(PLAYER_LIST_ITEM_ICON_SIZE.get());

            VBox pane = new VBox(icon);
            pane.getStyleClass().add("player-avatar");
            pane.setMaxSize(PLAYER_LIST_ITEM_ICON_SIZE.get(), PLAYER_LIST_ITEM_ICON_SIZE.get());

            return pane;
        }

        private VBox createPlayerFieldsInfo() {
            Label headLName = new Label("Имя");
            Label headLMoney = new Label("Деньги");

            headLName.getStyleClass().add("underline");
            headLMoney.getStyleClass().add("underline");

            HBox infoPosition = new HBox(headLName, new Label(": " + player.getName()));
            HBox infoMoney = new HBox(headLMoney, lMoney);

            return new VBox(infoPosition, infoMoney);
        }

        @Override
        public void update() {
            this.lMoney.setText(": " + player.getMoney());
        }
    }
}
