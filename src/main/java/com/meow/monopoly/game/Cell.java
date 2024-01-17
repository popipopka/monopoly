package com.meow.monopoly.game;

import com.meow.monopoly.general.CellType;
import javafx.scene.paint.Color;

import java.util.Map;

/**
 * Ячейка на игровом поле
 */
public class Cell {
    private final int position;
    private final String name;
    private final int cost;
    private final int upgradeCost;
    private final Map<Integer, Integer> rent;
    private final Color color;
    private final CellType type;

    private Player owner = null;
    private int level = 0;

    public Cell(int position,
                String name,
                int cost,
                int upgradeCost,
                Map<Integer, Integer> rent,
                Color color,
                CellType type) {
        this.position = position;
        this.name = name;
        this.cost = cost;
        this.upgradeCost = upgradeCost;
        this.rent = rent;
        this.color = color;
        this.type = type;
    }

    public int getPosition() {
        return position;
    }


    public String getName() {
        return name;
    }

    public int getCost() {
        return this.cost;
    }

    public int getUpgradeCost() {
        return upgradeCost;
    }

    public int getLevel() {
        return level;
    }

    public boolean isUpgradeable() {
        return level == rent.size(); 
    }

    public void increaseLevel() {
        if(isUpgradeable()) this.level++;
    }

    public int getRent() {
        return rent.get(level);
    }

    public Color getColor() {
        return color;
    }

    public CellType getType() {
        return type;
    }


    public boolean isOwned() {
        return this.owner != null;
    }


    public void setOwner(Player player) {
        this.owner = player;
    }

    public Player getOwner() {
        return owner;
    }

    public void delOwner() {
        this.owner = null;
    }
}
