package com.meow.monopoly.game;

import java.util.HashSet;
import java.util.Set;

import static com.meow.monopoly.general.settings.GameSettings.*;

public class Player {
    private final int id;
    private final String name;
    private int position = START_PLAYER_POSITION.get();
    private int money = START_PLAYER_MONEY.get();
    private final Set<Cell> objects = new HashSet<>();

    public Player(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Set<Cell> getObjects() {
        return objects;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void addObject(Cell object) {
        this.objects.add(object);
    }

    public void delObject(Cell object) {
        this.objects.remove(object);
    }

    public void addMoney(int value) {
        this.money += value;
    }

    public void delMoney(int value) {
        this.money -= value;
    }

    public int getMoney() {
        return money;
    }

    public int getPosition() {
        return this.position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        return id == player.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
