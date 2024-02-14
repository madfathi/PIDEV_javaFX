
package tn.esprit.guiapplication.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import tn.esprit.guiapplication.services.ClientService;
import tn.esprit.guiapplication.models.Client;

import java.sql.SQLException;

public class Ajouterclient {
    @FXML
    private TextField ageTF;

    @FXML
    private TextField nomTF;

    @FXML
    private TextField poidsTF;

    @FXML
    private TextField prenomTF;

    @FXML
    void afficherClient(ActionEvent event) {

    }

    @FXML
    void ajouterClient(ActionEvent event) {
        ClientService clientService = new ClientService();
        Client client = new Client();
        client.setNom(nomTF.getText());
        client.setPrenom(prenomTF.getText());
        client.setAge(Integer.parseInt(ageTF.getText()));
        client.setPoids(Integer.parseInt(poidsTF.getText()));
        try {
            clientService.ajouter(new Client(11, 77, "fhf", "ufh"));
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION) ;
            alert.setTitle("Success");
            alert.setContentText("client ajoutée");
            alert.showAndWait();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR) ;
            alert.setTitle("Erreur");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

}
