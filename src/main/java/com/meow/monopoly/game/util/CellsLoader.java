package com.meow.monopoly.game.util;

import com.meow.monopoly.general.CellType;
import com.meow.monopoly.game.Cell;
import javafx.scene.paint.Color;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Создает экземпляры класса {@code Cell}, загружая данные о них из cells.yaml
 */
public class CellsLoader {
    private final Map<Integer, Cell> cells;

    public CellsLoader() {
        Yaml yaml = new Yaml();
        List<LinkedHashMap<String, ?>> objects;

        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("cells.yaml")) {
            objects = yaml.load(inputStream);

        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }

        this.cells = objects.stream()
                .map(this::cellFromMap)
                .collect(Collectors.toMap(
                        Cell::getPosition,
                        cell -> cell
                ));
    }

    public Cell cellFromMap(Map<String, ?> map) {
        Color color = Color.valueOf("#cfe3c8");
        if (map.get("color") != null) {
            color = Color.web((String) map.get("color"));
        }

        @SuppressWarnings("unchecked")
        Map<Integer, Integer> rent = (Map<Integer, Integer>) map.get("rent");

        return new Cell(
                (int) map.get("position"),
                (String) map.get("name"),
                (int) map.get("cost"),
                (int) map.get("upgradeCost"),
                rent,
                color,
                CellType.valueOf((String) map.get("type"))
        );
    }

    public Map<Integer, Cell> get() {
        return this.cells;
    }
}
