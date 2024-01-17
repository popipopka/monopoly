package com.meow.monopoly.game;

import java.util.Random;

/**
 * Класс игральных костей
 */
public class Dice {
    private final int numDice;
    private static final int NUM_SIDES = 6;
    private final int[] value;

    private final Random rnd = new Random();

    public Dice(int numDice) {
        this.numDice = numDice;
        this.value = new int[numDice];
    }

    /**
     * Бросить игральные кости
     * @return значение на каждом из кубиков
     */
    public int[] roll() {
        for (int i = 0; i < numDice; i++) {
            value[i] = rnd.nextInt(0, NUM_SIDES) + 1;
        }
        return value;
    }

    /**
     * @return значение на каждом из кубиков
     */
    public int[] getValues() {
        return value;
    }
}
