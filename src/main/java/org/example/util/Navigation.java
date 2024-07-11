package org.example.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class Navigation {
    private static Parent rootNode;
    private static Scene scene;
    private static Stage stage;

    public static void switchNavigation(AnchorPane path, String event) throws IOException {
//        rootNode = FXMLLoader.load(Navigation.class.getResource("/view/" + path));
//
//        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        scene = new Scene(rootNode);
//        stage.setScene(scene);
//        stage.centerOnScreen();
//        stage.show();
    }

    public static void switchPaging(Pane ChangePane, String path, String name) throws IOException {
//        pane.getChildren().clear();
        Parent parent = FXMLLoader.load(Navigation.class.getResource("/view/"+path));
        ChangePane.getChildren().clear();
        ChangePane.getChildren().add(parent);
    }
}

