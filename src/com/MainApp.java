package com;

import com.manager.StringManager;
import com.manager.WindowManager;
import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import jdk.nashorn.api.scripting.JSObject;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.events.EventTarget;
import org.w3c.dom.html.HTMLElement;

import java.util.Set;

public class MainApp extends Application {

    private Stage primaryStage;
    private Pane rootLayout;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Config.init(this);

        this.primaryStage = primaryStage;
        rootLayout = WindowManager.load(Pane.class, "root.fxml");
        Scene scene = new Scene(rootLayout);
        primaryStage.setTitle(StringManager.getString("app_name"));
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream( "resource/images/app_icon.png" )));
        primaryStage.setMaximized(true);

        AnchorPane anchorPane = switchStage(AnchorPane.class, "browser.fxml");

        Button button = (Button) anchorPane.lookup("#btn");
//        button.setStyle("-fx-background-image: url('resource/images/app_icon.png')");


        System.setProperty("http.proxyHost","47.91.237.123");
        System.setProperty("http.proxyPort","8080");

      //  ParserManager.test("http://ulyanovsk.aport.ru/melkaja_kukhonnaja_tekhnika/cat495");


        WebView webView = (WebView) anchorPane.lookup("#webView");
        WebEngine engine = webView.getEngine();
        engine.setUserAgent("asd");
        engine.setJavaScriptEnabled(true);
//        engine.load("https://www.w3schools.com/html/tryit.asp?filename=tryhtml_script_html");
//        engine.load("http://134.0.117.236/test/");

        engine.getLoadWorker().stateProperty().addListener(new ChangeListener<Worker.State>() {
            @Override
            public void changed(ObservableValue<? extends Worker.State> observable, Worker.State oldValue, Worker.State newValue) {
                if(newValue== Worker.State.SCHEDULED){
                    System.out.println("state: scheduled");
                } else if(newValue== Worker.State.RUNNING){
                    System.out.println("state: running");
                } else if(newValue== Worker.State.SUCCEEDED){

                    Set<Node> scrollBars = webView.lookupAll(".model-name");
                    for (Node node : scrollBars) {
                        if (node instanceof ScrollBar) {
                            ScrollBar sBar = (ScrollBar) node;
                            sBar.setValue(100.0);
                        }
                    }

                    final BooleanProperty mouseOver = new SimpleBooleanProperty();
                    final JSObject window = (JSObject) engine.executeScript("window");
                    window.setMember("mouseOverProperty", mouseOver);


                    Document document = engine.getDocument();
                    NodeList a = document.getElementsByTagName("a");
                    int length = a.getLength();
                    for (int i = 0; i < length; i++) {
                        org.w3c.dom.Node item = a.item(i);

                    }
//                    ((HTMLElement) a.item(0)).click();
                    ((EventTarget) a.item(0)).addEventListener("click", evt -> {
                        System.out.println("state: running");
                    }, false);



                }
            }
        });

        engine.load("http://ulyanovsk.aport.ru/melkaja_kukhonnaja_tekhnika/cat495");



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
        rootLayout.getChildren().add(0, load);
        return load;
    }

}
