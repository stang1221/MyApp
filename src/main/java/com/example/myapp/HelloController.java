package com.example.myapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class HelloController {
    public static void main(String[] args) {
        GridPane pane = new GridPane();
        Label air = new Label("Annual Interest Rate");
        Label noy = new Label("Number of years");
        Label la = new Label("Loan amount: ");
        Label mp = new Label("Monthly payment");
        Label tp = new Label("Total payment");
        TextField airText = new TextField();
        TextField noyText = new TextField();
        TextField laText = new TextField();
        TextField mpText = new TextField();
        TextField tpText = new TextField();
        Button calcButton = new Button("Calculate!");
        pane.add(air,1,1);
        pane.add(airText, 2,1);
        pane.setVgap(10);
        pane.setHgap(10);
        pane.add(noy, 1, 2);
        pane.add(noyText,2, 2);
        pane.add(la, 1,3);
        pane.add(laText,2,3 );
        pane.add(mp, 1, 4);
        pane.add(mpText,2,4);
        pane.add(tp,1,5);
        pane.add(tpText,2,5);


        Scene scene = new Scene(pane, 600, 500);

    }

    public void onHelloButtonClick(ActionEvent actionEvent) {
    }
}