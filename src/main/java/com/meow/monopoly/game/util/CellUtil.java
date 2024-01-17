package com.meow.monopoly.game.util;

import com.meow.monopoly.general.CellType;
import com.meow.monopoly.game.Cell;

import java.util.Objects;

import static com.meow.monopoly.general.CellType.*;

public class CellUtil {
    private CellUtil() {
        throw new IllegalStateException("Utility class");
    }

    private static boolean checkType(CellType a, CellType b) {
        return Objects.equals(a, b);
    }

    public static boolean isObject(Cell cell) {
        return checkType(OBJECT, cell.getType());
    }

    public static boolean isJail(Cell cell) {
        return checkType(JAIL, cell.getType());
    }

    public static boolean isGoToJail(Cell cell) {
        return checkType(GOTO_JAIL, cell.getType());
    }

    public static boolean isInactive(Cell cell) {
        return checkType(INACTIVE, cell.getType());
    }
    public static boolean isTax(Cell cell) {
        return checkType(TAX, cell.getType());
    }

    public static boolean isChance(Cell cell) {
        return checkType(CHANCE, cell.getType());
    }

    public static boolean isChest(Cell cell) {
        return checkType(CHEST, cell.getType());
    }
}
