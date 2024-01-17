package com.meow.monopoly.gui.mainpanel;

import com.meow.monopoly.general.notifications.Notification;
import com.meow.monopoly.general.Updated;
import javafx.animation.FadeTransition;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class NotificationsGUI extends VBox {

    public NotificationsGUI() {
        this.getStyleClass().add("notification");
    }

    public void show(Notification notification) {
        if(notification == null || notification.get().isEmpty()) return;

        Label message = new Label(notification.get());
        this.getChildren().add(message);
        applyFadeTransition(message);
    }

    private void applyFadeTransition(Node node) {
        FadeTransition fade = new FadeTransition(Duration.seconds(4), node);

        fade.setFromValue(1);
        fade.setToValue(0);

        fade.setOnFinished(actionEvent -> this.getChildren().remove(node));
        fade.play();
    }
}
