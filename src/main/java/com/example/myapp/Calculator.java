package com.example.myapp;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Calculator extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        GridPane pane = new GridPane();
        Label air = new Label("Annual Interest Rate");
        Label noy = new Label("Number of Years");
        Label la = new Label("Loan Amount");
        Label mp = new Label("Monthly Payment");
        Label tp = new Label("Total Payment");
        TextField airText = new TextField();
        TextField noyText = new TextField();
        TextField laText = new TextField();
        TextField mpText = new TextField();
        TextField tpText = new TextField();
        Button calcButton = new Button("Calculate!");
        calcButton.setOnAction( (event) -> {
            double rate = Double.parseDouble(airText.getText());
            double years = Double.parseDouble(noyText.getText());
            double amount = Double.parseDouble(laText.getText());
            // calculate monthly payment and total payment
            mpText.setText("A big amount");
            tpText.setText("A hugh amount");
        });
        pane.add(air,1,1);
        pane.add(airText,2,1);
        pane.setVgap(10);
        pane.setHgap(10);
        pane.add(noy,1,2);
        pane.add(noyText,2,2);
        pane.add(la,1,3);
        pane.add(laText,2,3);
        pane.add(mp,1,4);
        pane.add(mpText,2,4);
        pane.add(tp,1,5);
        pane.add(tpText,2,5);
        pane.add(calcButton,2,6);

        Scene scene = new Scene(pane,300,250);
        stage.setScene(scene);
        stage.setTitle("Mortgage Calculator App");
        stage.show();

    }
}
