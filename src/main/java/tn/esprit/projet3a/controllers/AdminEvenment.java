package tn.esprit.projet3a.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import tn.esprit.projet3a.test.HelloApplication;

import java.io.IOException;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.control.Label;

public class AdminEvenment {

    @FXML
    private Label label;

    @FXML
    private Button ajouterAdmin;


    @FXML
    void modifierAdmin(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/ModifierEvenment.fxml"));
        try {
            label.getScene().setRoot(fxmlLoader.load());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

    }

    @FXML
    void ajouterAdmin(ActionEvent event) {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/AjouterEvenment.fxml"));
        try {
            label.getScene().setRoot(fxmlLoader.load());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

    }

    @FXML
    void rechercheAdmin(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/RechercheEvenment.fxml"));
        try {
            label.getScene().setRoot(fxmlLoader.load());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

    }

    @FXML
    void avisAdmin(ActionEvent event) {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/Avis.fxml"));
        try {
            label.getScene().setRoot(fxmlLoader.load());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }


    }

}
