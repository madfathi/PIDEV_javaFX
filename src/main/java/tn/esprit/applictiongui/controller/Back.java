package tn.esprit.applictiongui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import tn.esprit.applictiongui.test.HelloApplication;

import java.io.IOException;

public class Back {
    @FXML
    private Button c;

    @FXML
    void com(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/tn/esprit/applictiongui/affichecommande.fxml"));
        try {
            c.getScene().setRoot(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void pani(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/tn/esprit/applictiongui/afficherpanier.fxml"));
        try {
            c.getScene().setRoot(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
