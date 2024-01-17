package com.meow.monopoly.fxapp;

import com.meow.monopoly.general.settings.GUIIcons;
import com.meow.monopoly.gui.mainpanel.MonopolyGUI;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import org.kordamp.ikonli.javafx.FontIcon;

import java.util.Arrays;
import java.util.HashSet;

import static com.meow.monopoly.general.settings.GUISizes.*;

public class MonopolyController {
    @FXML
    public BorderPane rootPanel;

    @FXML
    public VBox controlBoard;

    @FXML
    public Button buyBtn;

    @FXML
    public Button upgradeBtn;
    
    @FXML
    public BorderPane sellPane;

    @FXML
    public Button sellBtn;

    @FXML
    public TextField sellInputField;

    @FXML
    public Button rentBtn;

    @FXML
    public Button nextTurnBtn;

    private MonopolyGUI game;

    @FXML
    private void initialize() {
        game = new MonopolyGUI(new HashSet<>(Arrays.asList("Егор", "Любимая", "Димка", "Бомж")));
        this.rootPanel.setCenter(game);

        setControlBoardSettings();
    }

    public void onClickBuyBtn() {
        game.buy();
    }

    public void onClickSellBtn() {
        if(sellInputField.getText().isEmpty()) return;

        int position = Integer.parseInt(sellInputField.getText());
        game.sell(position);

        sellInputField.setText(null);
    }

    public void onClickRentBtn() {
        game.rent();
    }

    public void onClickUpgradeBtn() {
        game.upgrade();
    }

    public void onClickNextTurnBtn() {
        game.nextTurn();
    }

    private void setControlBoardSettings() {
        controlBoard.setPrefSize(CONTROL_BOARD_WIDTH.get(), GAME_BOARD.get());
        controlBoard.setMaxSize(CONTROL_BOARD_WIDTH.get(), GAME_BOARD.get());

        buyBtn.setPrefSize(CONTROL_ELEM_WIDTH.get(), CONTROL_ELEM_HEIGHT.get());
        upgradeBtn.setPrefSize(CONTROL_ELEM_WIDTH.get(), CONTROL_ELEM_HEIGHT.get());
        setSellControlsSettings();
        rentBtn.setPrefSize(CONTROL_ELEM_WIDTH.get(), CONTROL_ELEM_HEIGHT.get());

        nextTurnBtn.setPrefSize(CONTROL_ELEM_WIDTH.get() * 2.07, CONTROL_ELEM_HEIGHT.get());
    }

    private void setSellControlsSettings() {
        sellPane.setMaxSize(CONTROL_ELEM_WIDTH.get(), CONTROL_ELEM_HEIGHT.get());

        sellBtn.setPrefSize(CONTROL_ELEM_HEIGHT.get(), CONTROL_ELEM_HEIGHT.get());
        sellInputField.setPrefSize(CONTROL_ELEM_WIDTH.get() * 0.6, CONTROL_ELEM_HEIGHT.get());

        FontIcon icon = new FontIcon(GUIIcons.DELETE.getName());
        icon.setIconSize((int) (CONTROL_ELEM_HEIGHT.get() * 0.6));

        sellBtn.setGraphic(icon);
        setNumericOnlyFilter();
    }

    private void setNumericOnlyFilter() {
        sellInputField.addEventFilter(KeyEvent.KEY_TYPED, event -> {
            String inputChar = event.getCharacter();
            if (!inputChar.matches("\\d")) {
                event.consume();
            }
        });
    }
}