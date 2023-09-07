package com.example.myapp;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GameGUI extends Application {



    @Override
    public void start(Stage primaryStage) throws Exception{
        GridPane pane = new GridPane();

        Label [] labels = new Label[6];
        String [] labelNames = {"Title", "System", "Developer", "Category","Rating", "Year"};
        for (int i = 0; i < labels.length; i++) {
            labels[i] = new Label(labelNames[i]);
            pane.add(labels[i], 1, 1 +i);
        }
        TextField [] textFields = new TextField[6];
        for (int i= 0; i < textFields.length; i++) {
            textFields[i] = new TextField();
            pane.add(textFields[i], 2, i+1);
        }



        Scene scene = new Scene(pane, 200, 250);
        primaryStage.setScene(scene);
        primaryStage.show();

        Button saveButton = new Button("Save");
        pane.add(saveButton, 1, 7);
        saveButton.setOnAction(e ->{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("The game has been saved");
            alert.show();
        });
        Button cancelButton = new Button("Cancel");
        pane.add(cancelButton, 1, 8);
        cancelButton.setOnAction(e ->{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("The game has been cancelled");
            alert.show();
        });



    }
}
