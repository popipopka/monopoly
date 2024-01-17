package com.meow.monopoly.gui.subpanel;

import com.meow.monopoly.game.Cell;
import com.meow.monopoly.general.Updated;
import com.meow.monopoly.gui.GUIUtil;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static com.meow.monopoly.general.settings.GUISizes.*;

public class ObjectsListGUI extends ScrollPane implements Updated {
    private final Set<Cell> objects;
    private final VBox content = new VBox();

    public ObjectsListGUI(Set<Cell> objects) {
        this.objects = objects;
        this.setContent(content);

        this.setPrefSize(LIST_WIDTH.get(), LIST_HEIGHT.get());

        this.content.getStyleClass().add("objects-list");

        this.content.setPrefSize(LIST_WIDTH.get(), LIST_HEIGHT.get());
        this.content.setMaxSize(LIST_WIDTH.get(), LIST_HEIGHT.get());
    }

    private void createObjectsGUI() {
        List<Cell> sortedObj = objects.stream()
                .sorted(Comparator.comparing(o -> o.getColor().toString()))
                .toList();

        ObjectListItem item;
        for (Cell cell : sortedObj) {
            item = new ObjectListItem(cell);
            content.getChildren().add(item);
        }
    }

    @Override
    public void update() {
        this.content.getChildren().clear();
        createObjectsGUI();
    }

    static class ObjectListItem extends HBox {
        public ObjectListItem(Cell cell) {
            this.getStyleClass().add("objects-item");

            this.setPrefSize(LIST_ITEM_WIDTH.get(), LIST_ITEM_HEIGHT.get());
            this.setMaxSize(LIST_ITEM_WIDTH.get(), LIST_ITEM_HEIGHT.get());

            Label name = new Label(cell.getName());
            name.getStyleClass().add("underline");
            name.setMinWidth(LIST_ITEM_WIDTH.get() * 0.3);
            name.setMaxWidth(LIST_ITEM_WIDTH.get() * 0.3);

            this.getChildren().addAll(
                    GUIUtil.createRect(OBJ_LIST_ITEM_RECT_SIZE.get(), OBJ_LIST_ITEM_RECT_SIZE.get(), cell.getColor()),
                    name,
                    new Label("Позиция\n" + cell.getPosition()),
                    new Label("Рента\n" + cell.getRent()),
                    new Label("Дома\n" + cell.getLevel())
            );
        }
    }
}