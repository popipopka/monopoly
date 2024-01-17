package com.meow.monopoly.game;

import com.meow.monopoly.general.notifications.Notification;
import javafx.scene.paint.Color;

import java.util.Map;
import java.util.function.Supplier;

import static com.meow.monopoly.general.notifications.BankNotifications.*;
import static com.meow.monopoly.general.settings.GameSettings.PLAYER_SALARY_SIZE;

/**
 * Реализация Банкира
 */
public class Bank {
    private final Map<Integer, Player> players;
    private final Map<Integer, Cell> objects;
    private final Map<Integer, Cell> taxes;

    public Bank(GameBoard gameBoard) {
        this.players = gameBoard.getPlayers();
        this.objects = gameBoard.getObjectsCells();
        this.taxes = gameBoard.getTaxesCells();
    }

    private boolean isCellNotFound(Supplier<Map<Integer, Cell>> supplier, int objPos) {
        return !supplier.get().containsKey(objPos);
    }

    private boolean isHeCuntPay(int money, int cost) {
        return money < cost;
    }

    private boolean isHeOwner(Player player, Cell object) {
        return object.getOwner().equals(player);
    }

    /**
     * Проверяет, владеет ли игрок полным набором объектов задаваемого цвета
     *
     * @param player игрок
     * @param color  цвет объектов
     * @return владеет ли игрок всем набором
     */
    private boolean isHeBoughtAllColoredObject(Player player, Color color) {
        int expectedCount = (int) this.objects.values()
                .stream()
                .filter(obj -> color.equals(obj.getColor()))
                .count();

        int actualCount = (int) player.getObjects()
                .stream()
                .filter(obj -> color.equals(obj.getColor()))
                .count();

        return expectedCount == actualCount;
    }

    /**
     * Покупает объект
     *
     * @param playerId id игрока
     * @param objPos   позиция объекта
     * @return уведомление о статусе покупки объекта
     */
    public Notification purchase(int playerId, int objPos) {
        if (isCellNotFound(() -> objects, objPos)) return OBJ_NOT_FOUND;

        Player pl = players.get(playerId);
        Cell obj = objects.get(objPos);

        String nP = pl.getName();
        String nO = obj.getName();

        if (obj.isOwned()) return OBJ_IS_OWNED.setPlayer(obj.getOwner().getName()).setObject(nO);
        if (isHeCuntPay(pl.getMoney(), obj.getCost())) return PLAYER_CANT_PURCHASE.setPlayer(nP).setObject(nO);

        int objCost = obj.getCost();

        pl.delMoney(objCost);
        pl.addObject(obj);
        obj.setOwner(pl);

        return PLAYER_PURCHASE.setPlayer(nP).setObject(nO);
    }

    /**
     * Продает объект
     *
     * @param playerId id игрока
     * @param objPos   позиция объекта
     * @return уведомление о статусе продажи объекта
     */
    public Notification sell(int playerId, int objPos) {
        if (isCellNotFound(() -> objects, objPos)) return OBJ_NOT_FOUND;

        Player pl = players.get(playerId);
        Cell obj = objects.get(objPos);

        String nP = pl.getName();
        String nO = obj.getName();

        if (!obj.isOwned()) return OBJ_IS_NOT_OWNED.setObject(nO);
        if (!isHeOwner(pl, obj)) return PLAYER_IS_NOT_OWNER.setPlayer(nP).setObject(nO);

        int objCost = obj.getCost();

        pl.addMoney(objCost);
        pl.delObject(obj);
        obj.delOwner();

        return OBJ_SOLD.setPlayer(nP).setObject(nO);
    }

    /**
     * Выплачивает ренту за объект
     *
     * @param playerId id игрока
     * @param objPos   позиция объекта
     * @return уведомление о статусе выплаты ренты за объект
     */
    public Notification rent(int playerId, int objPos) {
        if (isCellNotFound(() -> objects, objPos)) return NO_RENT_PAID_FOR_OBJ;

        Player pl = players.get(playerId);
        Cell obj = objects.get(objPos);

        String nP = pl.getName();
        String nO = obj.getName();

        if (!obj.isOwned()) return OBJ_IS_NOT_OWNED.setObject(nO);
        if (isHeOwner(pl, obj)) return PLAYER_IS_OWNER.setPlayer(nP).setObject(nO);

        if (isHeCuntPay(pl.getMoney(), obj.getRent())) return PLAYER_CANT_PAID_RENT.setPlayer(nP).setObject(nO);

        Player own = obj.getOwner();
        int objRent = obj.getRent();

        pl.delMoney(objRent);
        own.addMoney(objRent);

        return PLAYER_PAID_RENT.setPlayer(nP).setObject(nO);
    }

    /**
     * Улучшает объект
     *
     * @param playerId id игрока
     * @param objPos   позиция объекта
     * @return уведомление о статусе улучшения объекта
     */
    public Notification upgrade(int playerId, int objPos) {
        if (isCellNotFound(() -> objects, objPos)) return OBJ_IS_NOT_UPGRADEABLE;

        Player pl = players.get(playerId);
        Cell obj = objects.get(objPos);

        String nP = pl.getName();
        String nO = obj.getName();

        if (!obj.isOwned()) return OBJ_IS_NOT_OWNED.setObject(nO);
        if (!isHeOwner(pl, obj)) return PLAYER_IS_NOT_OWNER.setPlayer(nP).setObject(nO);

        if (!isHeBoughtAllColoredObject(pl, obj.getColor()))
            return PLAYER_DID_NOT_BUY_ALL_COLORED_OBJECTS.setPlayer(nP).setObject(nO);

        if (!obj.isUpgradeable()) return OBJ_HAS_MAX_LEVEL.setObject(nO);

        if (isHeCuntPay(pl.getMoney(), obj.getUpgradeCost()))
            return PLAYER_CANT_PAY_UPGRADE.setPlayer(nP).setObject(nO);

        pl.delMoney(obj.getUpgradeCost());
        obj.increaseLevel();

        return PLAYER_PAY_UPGRADE.setPlayer(nP).setObject(nO);
    }

    /**
     * Выплачивает зарплату игроку
     *
     * @return уведомление о статусе выплаты денег игроку
     */
    public Notification paySalaryTo(int playerId) {
        Player pl = players.get(playerId);

        pl.addMoney(PLAYER_SALARY_SIZE.get());
        return PLAYER_WAS_PAID_SALARY.setPlayer(pl.getName());
    }

    /**
     * Взимает с игрока налог
     *
     * @param playerId id игрока
     * @param taxPos   позиция объекта
     * @return уведомление о статусе выплаты налога
     */
    public Notification payTax(int playerId, int taxPos) {
        if (isCellNotFound(() -> taxes, taxPos)) return TAX_NOT_FOUND;

        Cell tax = taxes.get(taxPos);
        Player pl = players.get(playerId);

        String nP = pl.getName();
        String nT = tax.getName();

        if (isHeCuntPay(pl.getMoney(), tax.getRent())) return PLAYER_CANT_PAY_TAX.setPlayer(nP);

        pl.delMoney(tax.getRent());
        return PLAYER_PAY_TAX.setPlayer(nP).setObject(nT);
    }
}
