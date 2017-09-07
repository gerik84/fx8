package com.manager;

import com.Config;
import com.MainApp;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

/**
 * Created by pavel on 07.09.17.
 */
public class WindowManager {

    public static <T extends Node> T load(Class<T> clazz, String stageName) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Config.getInstance().application.getClass().getResource("view/" + stageName));
            Object stage = loader.load();
            if (stage.getClass() != clazz) {
                throw new ClassCastException("Not expected class");
            }
            return (T) stage;
        } catch (IOException ex) {
            throw new RuntimeException("fxml not found: " + stageName);
        }

    }
}
