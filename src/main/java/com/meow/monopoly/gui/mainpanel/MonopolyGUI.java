package com.meow.monopoly.gui.mainpanel;

import com.meow.monopoly.game.Monopoly;
import com.meow.monopoly.general.Updated;
import com.meow.monopoly.gui.subpanel.InfoBoardGUI;
import javafx.scene.layout.BorderPane;

import java.util.Set;

public class MonopolyGUI extends BorderPane implements Updated {
    private final Monopoly monopoly;

    private final GameBoardGUI gameBoardGUI;
    private final NotificationsGUI notifications;
    private final InfoBoardGUI infoBoardGUI;

    public MonopolyGUI(Set<String> playersNames) {
        this.monopoly = new Monopoly(playersNames);
        this.gameBoardGUI = new GameBoardGUI(this.monopoly.getGameBoard());
        this.notifications = new NotificationsGUI();
        this.infoBoardGUI = new InfoBoardGUI(
                this.monopoly.getDices(),
                this.monopoly.getPlayers(),
                this.monopoly.getCells()
        );

        initialize();

        this.setCenter(gameBoardGUI);
        this.gameBoardGUI.setCenter(notifications);
        this.setLeft(infoBoardGUI);
    }

    /**
     * Инициализация интерфейса при создании игры
     */
    private void initialize() {
        this.gameBoardGUI.update();
        this.infoBoardGUI.showNullInformation();
    }

    public void buy() {
        this.notifications.show(this.monopoly.buy());
        this.infoBoardGUI.update();
    }

    public void sell(int objPosition) {
        this.notifications.show(this.monopoly.sell(objPosition));
        this.infoBoardGUI.update();
    }

    public void rent() {
        this.notifications.show(this.monopoly.rent());
        this.infoBoardGUI.update();
    }

    public void upgrade() {
        this.notifications.show(this.monopoly.upgrade());
        this.infoBoardGUI.update();
    }

    public void nextTurn() {
        this.monopoly.nextTurn()
                .forEach(this.notifications::show);

        update();
    }

    @Override
    public void update() {
        this.gameBoardGUI.update();
        this.infoBoardGUI.showInformation(this.monopoly.getCurPlayer());
    }
}
