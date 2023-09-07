package com.example.myapp;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class CoffeeHouseGUI extends Application {

    private RadioButton regular;
    private RadioButton decaf;
    private CheckBox[] condiments;
    private CheckBox[] muffins;

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane pane = new BorderPane();
        pane.setTop(getTopPane());
        pane.setLeft(getLeftPane());
        pane.setBottom(getBottomPane());
        pane.setCenter(getCenterPane());
        pane.setRight(getRightPane());
        Scene scene = new Scene(pane, 600,400);
        stage.setScene(scene);
        stage.show();
    }

    private Node getRightPane() {
        //blueberry, chocolate chip, banana nut and bran.
        GridPane pane = new GridPane();
        pane.setVgap(10);
        pane.setHgap(10);
        pane.setPadding(new Insets(5,5,5,5));
        muffins = new CheckBox[4];
        String[] names = { "blueberry", "chocolate chip", "banana nut", "bran"};
        for (int i = 0; i < muffins.length; i++) {
            muffins[i] = new CheckBox();
            Label label = new Label(names[i]);
            pane.add(muffins[i], 1, i + 1);
            pane.add(label, 2, i + 1);
        }
        return pane;
    }

    private Node getCenterPane() {
        GridPane pane = new GridPane();
        pane.setVgap(10);
        pane.setHgap(10);
        pane.setPadding(new Insets(5,5,5,5));
        condiments = new CheckBox[5];
        String[] names = { "cream", "sugar", "artificial sweetener", "cinnamon", "caramel" };
        for (int i = 0; i < condiments.length; i++) {
            condiments[i] = new CheckBox();
            pane.add(condiments[i],1, 1+i);
            Label label = new Label(names[i]);
            pane.add(label, 2, 1+i);
        }
        return pane;
    }

    private Node getBottomPane() {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(10,10,10,10));
        Button calculate = new Button("Calculate");
        calculate.setOnAction( e -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText(getTotalPrice());
            alert.show();
        });
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().add(calculate);
        return hbox;
    }

    private String getTotalPrice() {
        double price = 0;
        if (regular.isSelected()) {
            price = 3.0;
        }
        if (decaf.isSelected()) {
            price = 3.0;
        }
        for (int i = 0; i < condiments.length; i++) {
            if (condiments[i].isSelected()) {
                price += 0.25;
            }
        }
        for (int i = 0; i < muffins.length; i++) {
            if (muffins[i].isSelected()) {
                price += 2.25;
            }
        }
        price = price + price *0.07;
        return "Your total price is $" + price;
    }

    public Node getLeftPane() {
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(5,5,5,5));
        Label label = new Label("Coffee");
        vbox.getChildren().add(label);
        ToggleGroup tg = new ToggleGroup();
        regular = new RadioButton();
        decaf = new RadioButton();
        regular.setToggleGroup(tg);
        decaf.setToggleGroup(tg);
        HBox hbox = new HBox(5);
        hbox.getChildren().addAll(new Label("Regular"), regular);
        vbox.getChildren().add(hbox);
        HBox hbox2 = new HBox(5);
        hbox2.getChildren().addAll(new Label("Decaf"), decaf);
        vbox.getChildren().add(hbox2);
        return vbox;
    }

    public Node getTopPane() {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(10,10,10,10));
        Label title = new Label("Joe's Coffee House");
        title.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 20));
        hbox.getChildren().add(title);
        hbox.setAlignment(Pos.CENTER);
        return hbox;
    }
}

