package tn.esprit.guiapplication.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import tn.esprit.guiapplication.test.HelloApplication;

import java.io.IOException;

public class Description {
    @FXML
    private Label b;

    @FXML
    private ImageView im;
    public void join(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/tn/esprit/guiapplication/Ajouterclient.fxml"));
        try {
            b.getScene().setRoot(fxmlLoader.load());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

    }
}
