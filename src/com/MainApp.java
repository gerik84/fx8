package com;

import com.manager.StringManager;
import com.manager.WindowManager;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.events.EventTarget;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Config.init(this);

        this.primaryStage = primaryStage;
        rootLayout = WindowManager.load(BorderPane.class, "root.fxml");
        Scene scene = new Scene(rootLayout);
        primaryStage.setTitle(StringManager.getString("app_name"));
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream( "resource/images/app_icon.png" )));

        AnchorPane anchorPane = switchStage(AnchorPane.class, "browser.fxml");

        Button button = (Button) anchorPane.lookup("#btn");
//        button.setStyle("-fx-background-image: url('resource/images/app_icon.png')");


        System.setProperty("http.proxyHost","47.91.237.123");
        System.setProperty("http.proxyPort","8080");

        WebView webView = (WebView) anchorPane.lookup("#webView");
        WebEngine engine = webView.getEngine();
        engine.setUserAgent("asd");
        engine.setJavaScriptEnabled(true);
//        engine.load("https://www.w3schools.com/html/tryit.asp?filename=tryhtml_script_html");
//        engine.load("http://134.0.117.236/test/");
        engine.load("http://127.0.0.1/test/");
        Document document = engine.getDocument();
        Element div = document.getElementById("div");
        ((EventTarget) div).addEventListener("click", new EventListener() {
            @Override
            public void handleEvent(Event evt) {
                System.out.printf("asdasd");
            }
        }, false);


        primaryStage.show();


//        button.setText(StringManager.getString("app_name"));
    }




    //    public void initRootLayout() {
//        try {
//            // Загружаем корневой макет из fxml файла.
//
//
//            // Отображаем сцену, содержащую корневой макет.
//            Scene scene = new Scene(rootLayout);
//            primaryStage.setScene(scene);
//            primaryStage.show();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
    public <T extends Node> T switchStage(Class<T> clazz, String stage) {
        T load = WindowManager.load(clazz, stage);
        rootLayout.setCenter(load);
        return load;
    }

}
