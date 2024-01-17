package com.meow.monopoly.gui.subpanel;

import com.meow.monopoly.game.Cell;
import com.meow.monopoly.game.Dice;
import com.meow.monopoly.game.Player;
import com.meow.monopoly.general.Updated;
import javafx.scene.layout.VBox;

import java.util.HashMap;
import java.util.Map;

import static com.meow.monopoly.general.settings.GUISizes.GAME_BOARD;
import static com.meow.monopoly.general.settings.GUISizes.INFO_BOARD_WIDTH;
import static com.meow.monopoly.general.settings.GameSettings.START_CELL_POSITION;

/**
 * Панель для отображения дополнительной информации
 */
public class InfoBoardGUI extends VBox implements Updated {
    private final Map<Integer, Player> players;
    private final Map<Integer, Cell> cells;

    private final DiceGUI dices;
    private final PlayersListGui playersList;
    private final Map<Integer, CellAvatar> cellsAvatars = new HashMap<>();
    private final Map<Integer, ObjectsListGUI> objectsLists = new HashMap<>();

    private int curPlayerId;

    public InfoBoardGUI(Dice dices, Map<Integer, Player> players, Map<Integer, Cell> cells) {
        this.dices = new DiceGUI(dices);
        this.players = players;
        this.cells = cells;
        this.playersList = new PlayersListGui(players);
        fillCellsAvatars();
        fillList();

        this.getStyleClass().add("info-board");

        this.setPrefSize(INFO_BOARD_WIDTH.get(), GAME_BOARD.get());
        this.setMaxSize(INFO_BOARD_WIDTH.get(), GAME_BOARD.get());
    }

    private void fillList() {
        for (int i = 0; i < players.size(); i++) {
            this.objectsLists.put(i, new ObjectsListGUI(players.get(i).getObjects()));
        }
    }

    private void fillCellsAvatars() {
        for (int i = 0; i < cells.size(); i++) {
            this.cellsAvatars.put(i, new CellAvatar(this.cells.get(i)));
        }
    }

    /**
     * Выводит информацию перед началом игры, то есть когда {@code curPlayerId} равняется -1
     */
    public void showNullInformation() {
        this.playersList.unselectAll();
        this.getChildren().addAll(
                this.dices,
                this.playersList,
                this.cellsAvatars.get(START_CELL_POSITION.get())
        );
    }

    /**
     * Выводит такую информацию об игроке, как {@code DiceGUI, PlayersListGUI, CellAvatar, ObjectListGUI}.
     * Перед выводом снимает выделения со всех игроков в {@code PlayersListGUI} и выделяет текущего игрока
     * @param playerId id игрока, информацию о котором нужно вывести
     */
    public void showInformation(int playerId) {
        this.curPlayerId = playerId;

        this.playersList.unselectAll();
        this.playersList.select(playerId);

        this.getChildren().clear();
        this.getChildren().addAll(
                this.dices,
                this.playersList,
                this.cellsAvatars.get(this.players.get(curPlayerId).getPosition()),
                this.objectsLists.get(curPlayerId)
        );

        update();
    }

    @Override
    public void update() {
        this.dices.update();
        this.playersList.update();
        this.cellsAvatars.get(this.players.get(curPlayerId).getPosition()).update();
        this.objectsLists.get(curPlayerId).update();
    }
}
