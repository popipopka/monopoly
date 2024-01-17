package com.meow.monopoly.general.notifications;

import static com.meow.monopoly.general.notifications.FormattingTokens.PLAYER;

public enum PrisonNotifications implements Notification {
    PLAYER_IS_PRISONER(PLAYER + " - преступник(-ца)"),
    PLAYER_IS_NOT_PRISONER(PLAYER + " посещает тюрьму в качестве гостя"),

    PLAYER_NOT_ON_JAIL_CELL(PLAYER + " не находится на ячейке 'Тюрьма'"),
    PLAYER_NOT_ON_GOTO_JAIL_CELL(PLAYER + " не находится на ячейке 'Попався, жулик!'"),

    PLAYER_GOTO_JAIL(PLAYER + " отправился(-ась) в тюрьму"),
    PLAYER_GET_OUT_JAIL(PLAYER + " вышел(-ла) из тюрьмы"),
    PLAYER_NOT_GET_OUT_JAIL(PLAYER + " не смог(-ла) выйти из тюрьмы");

    private String text;

    PrisonNotifications(String text) {
        this.text = text;
    }

    @Override
    public String get() {
        return text;
    }

    public PrisonNotifications setPlayer(String name) {
        this.text = this.text.replace(PLAYER.toString(), name.toUpperCase());
        return this;
    }
}
