package com.meow.monopoly.general.settings;

/**
 * Основные настройки
 */
public enum GameSettings {
    NUM_DICES(2),
    MIN_NUM_PLAYERS(2),
    MAX_NUM_PLAYERS(4),
    NUM_CELLS(40),

    START_PLAYER_MONEY(1000),
    PLAYER_SALARY_SIZE(200),

    START_PLAYER_POSITION(0),

    START_CELL_POSITION(0),
    JAIL_CELL_POSITION(10),
    PARKING_CELL_POSITION(20),
    GOTO_JAIL_CELL_POSITION(30);

    private final int value;

    GameSettings(int value) {
        this.value = value;
    }

    public int get() {
        return value;
    }
}
