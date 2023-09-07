package com.example.myapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        //FXMLLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Button myButton = new Button("My First Button");
        Button myButton2 = new Button("My Second Button");
        Image image = new Image("itza.jpg");
        ImageView view = new ImageView(image);
        view.setX(10);
        view.setY(180);
        view.setFitWidth(580);
        view.setFitHeight(160);
        view.setPreserveRatio(false);
        FlowPane pane = new FlowPane();
        pane.getChildren().addAll(myButton,myButton2,view);
        // add another button
        Scene scene = new Scene(pane, 800, 600);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
