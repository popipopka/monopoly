package com.meow.monopoly.game;

import com.meow.monopoly.general.notifications.Notification;

import java.util.HashSet;
import java.util.Set;

import static com.meow.monopoly.general.notifications.PrisonNotifications.*;
import static com.meow.monopoly.general.settings.GameSettings.NUM_DICES;

/**
 * Реализация тюремного процесса
 */
public class Prison {
    private final Dice dices;
    private final GameBoard gameBoard;
    private final Set<Player> prisoners = new HashSet<>();

    public Prison(GameBoard gameBoard) {
        this.dices = new Dice(NUM_DICES.get());
        this.gameBoard = gameBoard;
    }

    public boolean isPrisoner(int playerId) {
        Player pl = this.gameBoard.getPlayers().get(playerId);
        return prisoners.contains(pl);
    }

    private boolean isNotIdenticalPosition(Player player, Cell cell) {
        return player.getPosition() != cell.getPosition();
    }

    public Notification goToJail(int playerId) {
        Cell goToJail = this.gameBoard.getGoToJailCell();
        Player pl = this.gameBoard.getPlayers().get(playerId);

        String nP = pl.getName();

        if (isNotIdenticalPosition(pl, goToJail)) return PLAYER_NOT_ON_GOTO_JAIL_CELL.setPlayer(nP);
        if (prisoners.contains(pl)) return PLAYER_IS_PRISONER.setPlayer(nP);

        this.gameBoard.moveToJail(playerId);
        return PLAYER_GOTO_JAIL.setPlayer(nP);
    }

    public Notification getOutJail(int playerId) {
        Cell jail = this.gameBoard.getJailCell();
        Player pl = this.gameBoard.getPlayers().get(playerId);

        String nP = pl.getName();

        if (isNotIdenticalPosition(pl, jail)) return PLAYER_NOT_ON_JAIL_CELL.setPlayer(nP);
        if (!prisoners.contains(pl)) return PLAYER_IS_NOT_PRISONER.setPlayer(nP);

        int[] values = dices.roll();
        boolean isDiffValues = false;
        for (int i = 1; i < NUM_DICES.get(); i++) {
            if (values[i - 1] == values[i]) {
                isDiffValues = true;
                break;
            }
        }

        return isDiffValues
                ? PLAYER_NOT_GET_OUT_JAIL.setPlayer(nP)
                : PLAYER_GET_OUT_JAIL.setPlayer(nP);
    }
}
