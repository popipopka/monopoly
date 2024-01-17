package com.meow.monopoly.fxapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MonopolyApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MonopolyApplication.class.getResource("game-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);

//        stage.setResizable(false);
        stage.setMaximized(true);
        stage.setTitle("Monopoly");
        stage.setScene(scene);
        stage.show();
    }

    //TODO не давать возможность ходить игрокам, которые наступили на объект, у которого есть собственник
    public static void main(String[] args) {
        launch();
    }
}