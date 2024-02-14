package tn.esprit.applictiongui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import tn.esprit.applictiongui.model.commande;
import tn.esprit.applictiongui.service.commandeservice;
import tn.esprit.applictiongui.test.HelloApplication;


import java.io.IOException;
import java.sql.SQLException;

public class Ajoutercommande {
    @FXML
    private TextField a;

    @FXML
    private TextField e;

    @FXML
    private TextField n;

    @FXML
    private TextField p;

    @FXML
    private TextField t;

    @FXML
    void ajoutercommande(ActionEvent event)  {
        commandeservice cs=new commandeservice();
        commande co=new commande();
        co.setNom(n.getText());
        co.setPre(p.getText());
        co.setMail(e.getText());
        co.setAddr(a.getText());
        co.setTel(Integer.parseInt(t.getText()));

        try {
            cs.ajouter(co);
            Alert alert= new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("success");
            alert.setContentText("personne ajoutee");
            alert.showAndWait();
        } catch (SQLException ex) {

            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText(String.valueOf(e.getShape()));
            alert.showAndWait();
        }
    }
    @FXML
    void affichecommande(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/tn/esprit/applictiongui/affichecommande.fxml"));
        try {
            n.getScene().setRoot(fxmlLoader.load());
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
   // @FXML
    /*void annullercommande(ActionEvent event) {

    }*/
}
