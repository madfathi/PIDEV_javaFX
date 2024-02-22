package tn.esprit.guiapplication.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import tn.esprit.guiapplication.models.Client;
import tn.esprit.guiapplication.models.Program;
import tn.esprit.guiapplication.services.ClientService;
import tn.esprit.guiapplication.services.ProgramService;
import tn.esprit.guiapplication.test.HelloApplication;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class Ajouterprogram {


    @FXML
    private TextField descriptionTF;

    @FXML
    private TextField id_cTF;

    @FXML
    private TextField niveauTF;

    @FXML
    private TextField prixTF;

    @FXML
    private TextField titreTF;


    @FXML
    void ajouterProgram(ActionEvent event) {
        ProgramService po = new ProgramService();
        Program ps = new Program();
        ClientService cs =new ClientService();
        Client c=new Client();
        c=cs.getClientById(Integer.parseInt(id_cTF.getText()));
        ps.setTitre(titreTF.getText());
        ps.setNiveau(niveauTF.getText());
        ps.setDescription(descriptionTF.getText());
        ps.setPrix(Integer.parseInt(prixTF.getText()));
        ps.setClient(c);
        ps.setId_p(Integer.parseInt(id_cTF.getText()));
        try {
            po.ajouter(ps);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION) ;
            alert.setTitle("Success");
            alert.setContentText("program ajoutée");
            alert.showAndWait();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR) ;
            alert.setTitle("Erreur");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    public void afficherProgram(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/tn/esprit/guiapplication/Afficherprogram.fxml"));
        try {
            titreTF.getScene().setRoot(fxmlLoader.load());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

    }

    public void retour2(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/tn/esprit/guiapplication/Afficherclient.fxml"));
        try {
            titreTF.getScene().setRoot(fxmlLoader.load());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

    }
}

