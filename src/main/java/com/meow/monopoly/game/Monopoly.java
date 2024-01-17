package com.meow.monopoly.game;

import com.meow.monopoly.game.util.CellUtil;
import com.meow.monopoly.general.notifications.Notification;

import java.util.*;

import static com.meow.monopoly.general.settings.GameSettings.*;

/**
 * Реализация Монополии
 */
public class Monopoly {
    private final GameBoard gameBoard;
    private final Dice dices;

    private final Bank bank;
    private final Prison prison;

    private int curPlayerId = -1;


    public Monopoly(Set<String> playersNames) {
        this.gameBoard = new GameBoard(playersNames);
        checkPlayersCount();

        this.bank = new Bank(this.gameBoard);
        this.dices = new Dice(NUM_DICES.get());
        this.prison = new Prison(this.gameBoard);
    }

    private void checkPlayersCount() {
        if (this.gameBoard.getCountPlayers() < MIN_NUM_PLAYERS.get()) {
            throw new IllegalArgumentException("Кол-во игроков меньше минимально допустимого значения");
        }

        if (this.gameBoard.getCountPlayers() > MAX_NUM_PLAYERS.get()) {
            throw new IllegalArgumentException("Кол-во игроков превышает максимально допустимое значение");
        }
    }

    /**
     * Направляет текущего игрока в банк с целью покупки объекта, на котором он находится
     *
     * @return уведомление о покупке
     */
    public Notification buy() {
        return bank.purchase(this.curPlayerId, this.gameBoard.getPlayerPosition(curPlayerId));
    }

    /**
     * Направляет текущего игрока в банк с целью продажи объекта с задаваемой позицией
     *
     * @param objPosition позиция продаваемого объекта
     * @return уведомление о продаже
     */
    public Notification sell(int objPosition) {
        return bank.sell(this.curPlayerId, objPosition);
    }

    /**
     * Направляет текущего игрока в банк с целью выплаты ренты за объект, на котором он находится
     *
     * @return уведомление о покупке
     */
    public Notification rent() {
        return bank.rent(this.curPlayerId, this.gameBoard.getPlayerPosition(curPlayerId));
    }

    /**
     * Направляет текущего игрока в банк с целью улучшения объекта, на котором он находится
     *
     * @return уведомление о покупке
     */
    public Notification upgrade() {
        return bank.upgrade(this.curPlayerId, this.gameBoard.getPlayerPosition(curPlayerId));
    }

    /**
     * Направляет текущего игрока в банк с целью выплаты налога за объект, на котором он находится
     *
     * @return уведомление о выплате налога
     */
    private Notification payTax() {
        return bank.payTax(this.curPlayerId, this.gameBoard.getPlayerPosition(curPlayerId));
    }

    /**
     * Направляет текущего игрока в банк с целью получения зарплаты
     *
     * @return уведомление о получении зарплаты
     */
    private Notification paySalary() {
        return bank.paySalaryTo(curPlayerId);
    }


    /**
     * Направляет текущего игрока в тюрьму как заключенного/гостя
     *
     * @return уведомление о заключении игрока
     */
    private Notification goToJail() {
        return prison.goToJail(curPlayerId);
    }

    /**
     * Решает, выходит ли игрок из тюрьмы или он остается заключенным
     *
     * @return уведомление о рассмотрении заключения игрока
     */
    private Notification getOutJail() {
        return prison.getOutJail(curPlayerId);
    }

    /**
     * Переход хода другому игроку. Здесь же происходит выплата зарплаты игроку, если он пересек ячейку старта
     * и автоматически выполняется действие ячейки на игрока с типом, отличным от {@code OBJECT}
     */
    public List<Notification> nextTurn() {
        List<Notification> n = new ArrayList<>();
        setCurPlayer();

        if (prison.isPrisoner(curPlayerId)) getOutJail();

        int[] values = this.dices.roll();

        int sumValues = Arrays.stream(values).sum();
        if (this.gameBoard.move(curPlayerId, sumValues)) n.add(paySalary());

        if (!CellUtil.isObject(this.gameBoard.getPlayerCell(curPlayerId))) n.add(performCellAction());

        return n;
    }

    /**
     * Выполняет действие, связанное с типом ячейки, на которой находится игрок
     * @return уведомление о совершенном действии
     */
    private Notification performCellAction() {
        Notification n;
        switch (this.gameBoard.getPlayerCell(curPlayerId).getType()) {
            case JAIL -> n = getOutJail();
            case GOTO_JAIL -> n = goToJail();
            case TAX -> n = payTax();

            default -> n = null;
        }
        return n;
    }

    /**
     * Устанавливает {@code curPlayerId} на текущего игрока
     */
    private void setCurPlayer() {
        this.curPlayerId = (this.curPlayerId + 1) % this.gameBoard.getCountPlayers();
    }

    public GameBoard getGameBoard() {
        return this.gameBoard;
    }

    public Map<Integer, Player> getPlayers() {
        return this.gameBoard.getPlayers();
    }

    public Map<Integer, Cell> getCells() {
        return this.gameBoard.getCells();
    }

    public int getCurPlayer() {
        return curPlayerId;
    }

    public Dice getDices() {
        return this.dices;
    }
}
