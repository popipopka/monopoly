package com.meow.monopoly.general.notifications;

import static com.meow.monopoly.general.notifications.FormattingTokens.OBJECT;
import static com.meow.monopoly.general.notifications.FormattingTokens.PLAYER;

public enum BankNotifications implements Notification {
    OBJ_NOT_FOUND("Данный объект не находится в собственности банка"),
    TAX_NOT_FOUND("За данный объект не выплачивается налог"),
    OBJ_IS_NOT_UPGRADEABLE("Данный объект нельзя улучшить"),
    NO_RENT_PAID_FOR_OBJ("За данный объект не выплачивается рента"),

    OBJ_IS_OWNED(OBJECT + " находится в собственности " + PLAYER),
    OBJ_IS_NOT_OWNED(OBJECT + " не имеет собственника. Сначала купите его"),

    OBJ_SOLD(OBJECT + " успешно продан игроком " + PLAYER),

    PLAYER_IS_OWNER(PLAYER + " является собственником(-цей) " + OBJECT),
    PLAYER_IS_NOT_OWNER(PLAYER + " не является собственником(-цей) " + OBJECT),

    PLAYER_CANT_PURCHASE(PLAYER + " не может позволить купить себе " + OBJECT),
    PLAYER_PURCHASE(OBJECT + " переходит в собственность игрока " + PLAYER),

    PLAYER_PAID_RENT(PLAYER + " выплатил(-а) ренту за " + OBJECT),
    PLAYER_CANT_PAID_RENT(PLAYER + " не смог выплатить ренту за " + OBJECT),

    PLAYER_DID_NOT_BUY_ALL_COLORED_OBJECTS(PLAYER + " не купил(-а) все объекты того же цвета, что и " + OBJECT),
    OBJ_HAS_MAX_LEVEL(OBJECT + " имеет максимально допустимый уровень"),

    PLAYER_CANT_PAY_UPGRADE(PLAYER + " не может позволить себе улучшить " + OBJECT),
    PLAYER_PAY_UPGRADE(PLAYER + " улучшил " + OBJECT),

    PLAYER_WAS_PAID_SALARY(PLAYER + " получил(-а) зарплату на свой счет"),

    PLAYER_CANT_PAY_TAX(PLAYER + " не может расплатиться за " + OBJECT),
    PLAYER_PAY_TAX(PLAYER + " расплатился(-ась) за " + OBJECT);

    private String text;

    BankNotifications(String text) {
        this.text = text;
    }

    public BankNotifications setPlayer(String name) {
        this.text = this.text.replace(PLAYER.toString(), name.toUpperCase());
        return this;
    }

    public BankNotifications setObject(String name) {
        this.text = this.text.replace(OBJECT.toString(), name.toUpperCase());
        return this;
    }

    @Override
    public String get() {
        return text;
    }
}
