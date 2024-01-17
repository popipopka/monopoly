package com.meow.monopoly.gui.subpanel;

import com.meow.monopoly.game.Dice;
import com.meow.monopoly.general.Updated;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.HashMap;
import java.util.Map;

import static com.meow.monopoly.general.settings.GameSettings.NUM_DICES;
import static com.meow.monopoly.general.settings.GUISizes.FACE_DICE;
import static com.meow.monopoly.general.settings.GUISizes.FACE_DICE_CIRCLE;

public class DiceGUI extends FlowPane implements Updated {
    private final Dice dices;
    private final Map<Integer, FaceDiceGUI> faces = new HashMap<>();

    public DiceGUI(Dice dices) {
        this.dices = dices;

        this.getStyleClass().add("dice");

        createFaces();
    }

    @Override
    public void update() {
        int[] values = this.dices.getValues();

        for (int i = 0; i < values.length; i++) {
            this.faces.get(i).setValue(values[i]);
        }
    }

    private void createFaces() {
        FaceDiceGUI face;
        for (int i = 0; i < NUM_DICES.get(); i++) {
            face = new FaceDiceGUI();

            this.faces.put(i, face);
            this.getChildren().add(face);
        }
    }

    /**
     * Отображение грани игральной кости
     */
    private static class FaceDiceGUI extends GridPane implements Updated {
        private int value = 0;

        public FaceDiceGUI() {
            this.setMaxSize(FACE_DICE.get(), FACE_DICE.get());
            this.setPrefSize(FACE_DICE.get(), FACE_DICE.get());

            this.getStyleClass().add("face-dice");
        }

        public void setValue(int value) {
            this.value = value;
            update();
        }

        private void clear() {
            this.getChildren().clear();
        }

        @Override
        public void update() {
            clear();
            switch (value) {
                case 1: {
                    this.add(getCircle(Color.valueOf("#cfe3c8")), 0, 2);
                    this.add(getCircle(Color.valueOf("#000000CE")), 1, 1);
                    this.add(getCircle(Color.valueOf("#cfe3c8")), 2, 0);
                    break;
                }

                case 2: {
                    this.add(getCircle(Color.valueOf("#000000CE")), 2, 0);
                    this.add(getCircle(Color.valueOf("#000000CE")), 0, 2);
                    break;
                }

                case 3: {
                    this.add(getCircle(Color.valueOf("#000000CE")), 0, 2);
                    this.add(getCircle(Color.valueOf("#000000CE")), 1, 1);
                    this.add(getCircle(Color.valueOf("#000000CE")), 2, 0);
                    break;
                }

                case 4: {
                    this.add(getCircle(Color.valueOf("#000000CE")), 0, 0);
                    this.add(getCircle(Color.valueOf("#000000CE")), 0, 2);
                    this.add(getCircle(Color.valueOf("#cfe3c8")), 1, 1);
                    this.add(getCircle(Color.valueOf("#000000CE")), 2, 0);
                    this.add(getCircle(Color.valueOf("#000000CE")), 2, 2);
                    break;
                }

                case 5: {
                    this.add(getCircle(Color.valueOf("#000000CE")), 0, 0);
                    this.add(getCircle(Color.valueOf("#000000CE")), 0, 2);
                    this.add(getCircle(Color.valueOf("#000000CE")), 1, 1);
                    this.add(getCircle(Color.valueOf("#000000CE")), 2, 0);
                    this.add(getCircle(Color.valueOf("#000000CE")), 2, 2);
                    break;
                }

                case 6: {
                    this.add(getCircle(Color.valueOf("#000000CE")), 0, 0);
                    this.add(getCircle(Color.valueOf("#000000CE")), 0, 1);
                    this.add(getCircle(Color.valueOf("#000000CE")), 0, 2);
                    this.add(getCircle(Color.valueOf("#cfe3c8")), 1, 1);
                    this.add(getCircle(Color.valueOf("#000000CE")), 2, 0);
                    this.add(getCircle(Color.valueOf("#000000CE")), 2, 1);
                    this.add(getCircle(Color.valueOf("#000000CE")), 2, 2);
                    break;
                }

                default:
                    break;
            }
        }

        private Circle getCircle(Color color) {
            return new Circle(FACE_DICE_CIRCLE.get(), color);
        }
    }
}
