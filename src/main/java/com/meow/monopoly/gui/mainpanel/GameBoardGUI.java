package com.meow.monopoly.gui.mainpanel;

import com.meow.monopoly.game.Cell;
import com.meow.monopoly.game.GameBoard;
import com.meow.monopoly.game.Player;
import com.meow.monopoly.general.Updated;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import static com.meow.monopoly.general.settings.GameSettings.*;
import static com.meow.monopoly.general.settings.GUISizes.*;

public class GameBoardGUI extends BorderPane implements Updated {
    private final HBox top = new HBox();
    private final HBox bottom = new HBox();
    private final VBox left = new VBox();
    private final VBox right = new VBox();

    private final GameBoard gameBoard;
    private final Map<Integer, CellGUI> cellsGUI = new HashMap<>();
    private final Map<Integer, PlayerGUI> playersGUI = new HashMap<>();

    public GameBoardGUI(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
        setViewSettings();
        createAllCellsGUI();
        createAllPlayersGUI();
    }

    /**
     * Обновляет {@code GameBoardGUI}
     */
    @Override
    public void update() {
        this.playersGUI.forEach(
                (key, value) -> this.cellsGUI
                        .get(value.getPosition())
                        .addPlayer(value)
        );
    }

    /**
     * Создает все {@code PlayerGUI}
     */
    public void createAllPlayersGUI() {
        this.gameBoard.getPlayers().forEach(
                (key, value) -> this.playersGUI.put(
                        key,
                        new PlayerGUI(value))
        );
    }

    private void setViewSettings() {
        double size = GAME_BOARD.get();
        double partSize = GAME_BOARD_PART.get();

        this.getStyleClass().add("game-board");

        this.setTop(top);
        this.setBottom(bottom);
        this.setLeft(left);
        this.setRight(right);

        this.setPrefSize(size, size);
        this.setMaxSize(size, size);

        this.top.setMaxSize(size, partSize * 2);
        this.top.setPrefSize(size, partSize * 2);

        this.bottom.setMaxSize(size, partSize * 2);
        this.bottom.setPrefSize(size, partSize * 2);

        this.left.setMaxSize(partSize * 2, size);
        this.left.setPrefSize(partSize * 2, size);

        this.right.setMaxSize(partSize * 2, size);
        this.right.setPrefSize(partSize * 2, size);
    }

    /**
     * Создает все {@code CellGUI} и помещает их в
     * {@code this.top}, {@code this.right}, {@code this.bottom} или {@code this.left}
     */
    private void createAllCellsGUI() {
        //top-left
        createCellsGUI(
                START_CELL_POSITION.get(), START_CELL_POSITION.get(),
                LEFT_RIGHT_WIDTH.get(), TOP_BOTTOM_HEIGHT.get(),
                () -> this.top, true
        );

        //top
        createCellsGUI(
                1, 9,
                TOP_BOTTOM_WIDTH.get(), TOP_BOTTOM_HEIGHT.get(),
                () -> this.top, true
        );

        //top-right
        createCellsGUI(
                JAIL_CELL_POSITION.get(), JAIL_CELL_POSITION.get(),
                LEFT_RIGHT_WIDTH.get(), TOP_BOTTOM_HEIGHT.get(),
                () -> this.top, true
        );

        //right
        createCellsGUI(
                11, 19,
                LEFT_RIGHT_WIDTH.get(), LEFT_RIGHT_HEIGHT.get(),
                () -> this.right, true
        );

        //bottom-right
        createCellsGUI(
                PARKING_CELL_POSITION.get(), PARKING_CELL_POSITION.get(),
                LEFT_RIGHT_WIDTH.get(), TOP_BOTTOM_HEIGHT.get(),
                () -> this.bottom, false
        );

        //bottom
        createCellsGUI(
                21, 29,
                TOP_BOTTOM_WIDTH.get(), (TOP_BOTTOM_HEIGHT.get()),
                () -> this.bottom, false
        );

        //bottom-left
        createCellsGUI(
                GOTO_JAIL_CELL_POSITION.get(), GOTO_JAIL_CELL_POSITION.get(),
                LEFT_RIGHT_WIDTH.get(), TOP_BOTTOM_HEIGHT.get(),
                () -> this.bottom, false
        );

        //left
        createCellsGUI(
                31, 39,
                LEFT_RIGHT_WIDTH.get(), (LEFT_RIGHT_WIDTH.get()),
                () -> this.left, false
        );
    }

    /**
     * Создает ячейки и помещает их в определенную секцию {@code BorderPane}.
     * То есть в секции {@code  top, bottom, left, right}
     *
     * @param start начальная позиция, с которой будет создаваться {@code CellGUI} для {@code Cell}
     * @param end   конечная позиция
     * @param pane  секция, куда будет помещаться {@code CellGUI}
     */
    private void createCellsGUI(int start, int end, int width, int height, Supplier<Pane> pane, boolean direction) {
        Map<Integer, Cell> cells = this.gameBoard.getCells();

        CellGUI cellGUI;
        for (int i = start; i <= end; i++) {
            cellGUI = new CellGUI(width, height, cells.get(i));
            this.cellsGUI.put(i, cellGUI);

            if (direction) {
                pane.get().getChildren().addLast(cellGUI);
            } else pane.get().getChildren().addFirst(cellGUI);
        }
    }
}
