package com.meow.monopoly.game;

import com.meow.monopoly.game.util.CellUtil;
import com.meow.monopoly.game.util.CellsLoader;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.meow.monopoly.general.settings.GameSettings.*;

/**
 * Игровое поле с ячейками и игроками
 */
public class GameBoard {
    private final Map<Integer, Cell> cells = new HashMap<>();
    private final Map<Integer, Player> players = new HashMap<>();

    public GameBoard(Set<String> playersNames) {
        fillCells();
        createPlayers(playersNames);
    }

    /**
     * Создает игроков
     *
     * @param names имена игроков
     */
    private void createPlayers(Set<String> names) {
        names.forEach(name ->
                this.players.put(
                        this.players.size(),
                        new Player(this.players.size(), name)
                )
        );
    }

    /**
     * Заполняет поле ячеек по данным из {@code CellsLoader}
     */
    private void fillCells() {
        CellsLoader container = new CellsLoader();
        this.cells.putAll(container.get());
    }

    /**
     * Получает позицию конкретного игрока
     *
     * @param playerId id игрока
     * @return позиция игрока
     */
    public int getPlayerPosition(int playerId) {
        return this.players.get(playerId).getPosition();
    }

    /**
     * Получает ячейку на которой стоит игрок
     *
     * @param playerId id игрока
     * @return ячейка, на которой стоит игрок
     */
    public Cell getPlayerCell(int playerId) {
        return this.cells.get(this.players.get(playerId).getPosition());
    }

    /**
     * Перемещает игрока вперед на указанное значение
     *
     * @param playerId id игрока
     * @param value    значение на игральных костях
     * @return прошел ли игрок ячейку старта
     */
    public boolean move(int playerId, int value) {
        Player player = this.players.get(playerId);

        boolean isStartPassed = (player.getPosition() + value) >= NUM_CELLS.get();
        player.setPosition((player.getPosition() + value) % NUM_CELLS.get());

        return isStartPassed;
    }

    public void moveToJail(int playerId) {
        Player player = this.players.get(playerId);
        player.setPosition(JAIL_CELL_POSITION.get());
    }

    /**
     * @return игроки на поле
     */
    public Map<Integer, Player> getPlayers() {
        return this.players;
    }

    /**
     * @return кол-во игроков на поле
     */
    public int getCountPlayers() {
        return this.players.size();
    }

    /**
     * @return ячейки поля
     */
    public Map<Integer, Cell> getCells() {
        return this.cells;
    }

    /**
     * @return ячейки с типом {@code OBJECT}
     */
    public Map<Integer, Cell> getObjectsCells() {
        return cells.entrySet()
                .stream()
                .filter(entry -> CellUtil.isObject(entry.getValue()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue
                ));
    }

    public Map<Integer, Cell> getTaxesCells() {
        return cells.entrySet()
                .stream()
                .filter(entry -> CellUtil.isTax(entry.getValue()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue
                ));
    }

    /**
     * @return ячейка с типом {@code JAIL}
     */
    public Cell getJailCell() {
        return this.cells.get(JAIL_CELL_POSITION.get());
    }

    /**
     * @return ячейка с типом {@code GOTO_JAIL}
     */
    public Cell getGoToJailCell() {
        return this.cells.get(GOTO_JAIL_CELL_POSITION.get());
    }
}
