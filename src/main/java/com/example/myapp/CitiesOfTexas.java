package com.example.myapp;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CitiesOfTexas extends Application {

    private Scene scene2;
    private Scene scene1;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Stage window = primaryStage;
        Label label1 = new Label("Cities of Texas Travel Guide");
        //Houston
        Button button1 = new Button("Houston, Texas");
        button1.setOnAction(e -> window.setScene(scene2));

        //San Antonio
        Button button2 = new Button("San Antonio,Texas");
        button2.setOnAction(e -> window.setScene (scene1));

        //layout 1 for window
        VBox layout1 = new VBox(20);
        layout1.getChildren().addAll(label1,button1);
        Scene scene1 = new Scene(layout1, 200, 200);

        //layout 2 for houston
        Label label2 = new Label("Information about Houston");
        StackPane layout2 = new StackPane();
        layout2.getChildren().addAll(label2, button2);
        Scene scene2 = new Scene(layout2, 200, 200);
        window.setScene(scene1);
        window.setTitle("Cities of Texas Travel Guide");
        window.setScene(scene2);
        window.setTitle("Houston");
        window.show();




    }
}
