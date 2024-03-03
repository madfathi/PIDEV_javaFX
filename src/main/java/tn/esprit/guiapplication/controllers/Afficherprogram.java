package tn.esprit.guiapplication.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import tn.esprit.guiapplication.Cellule.ClientCell;
import tn.esprit.guiapplication.Cellule.ProgramCell;
import tn.esprit.guiapplication.models.Client;
import tn.esprit.guiapplication.models.Program;
import tn.esprit.guiapplication.services.ClientService;
import tn.esprit.guiapplication.services.ProgramService;
import tn.esprit.guiapplication.test.HelloApplication;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.List;





public class Afficherprogram {

    @FXML
    private TextField descriptionTFmP;

    @FXML
    private TextField idTFmP;

    @FXML
    private ListView<Program> listView2;

    @FXML
    private TextField niveauTFmP;

    @FXML
    private TextField prixTFmP;

    @FXML
    private TextField titreTFmP;
    @FXML
    private TextField nom;



    @FXML
    void initialize() {

        ProgramService programService = new ProgramService();
        try {
            List<Program> programs = programService.recuperer();
            listView2.setCellFactory(param -> new ProgramCell());
            ObservableList<Program> observableList = FXCollections.observableList(programs);
            listView2.setItems(observableList);


            listView2.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1) {
                    Program selectedProgram = listView2.getSelectionModel().getSelectedItem();
                    if (selectedProgram != null) {

                        nom.setText(String.valueOf(selectedProgram.getClient().getNom()));
                        titreTFmP.setText(selectedProgram.getTitre());
                        niveauTFmP.setText(selectedProgram.getNiveau());
                        descriptionTFmP.setText(selectedProgram.getDescription());
                        prixTFmP.setText(String.valueOf(selectedProgram.getPrix()));
                    }
                }
            });
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }


    @FXML
    void modifierProgram(ActionEvent event) {
        ProgramService programService = new ProgramService();
        Program program = new Program();
        Client c = new Client();
        ClientService cs = new ClientService();
        try {
            c = cs.getClientByName(nom.getText());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

       program.setId_p(listView2.getSelectionModel().getSelectedItem().getId_p());

        program.setTitre(titreTFmP.getText());
        program.setClient(c);
        program.setNiveau(niveauTFmP.getText());
        program.setDescription(descriptionTFmP.getText());
        program.setPrix(Integer.parseInt(prixTFmP.getText()));

        try {
            programService.modifier(program);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Succes");
            alert.setContentText("Programme modifié");
            alert.showAndWait();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
        initialize();
    }




    public void supprimerprogram(ActionEvent actionEvent) {
        ProgramService programService = new ProgramService();
        Program program = new Program();
        program.setTitre(titreTFmP.getText());
        try {
            programService.supprimer(program.getTitre());
            Alert alert =new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Succes");
            alert.setContentText("Program supprimée");
            alert.showAndWait();
            initialize();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText(e.getMessage());
            alert.showAndWait();

        }
    }
    @FXML
    void clearFields(ActionEvent event) {
        titreTFmP.clear();
        prixTFmP.clear();
        niveauTFmP.clear();
        descriptionTFmP.clear();
        nom.clear();

    }



    public void retour3(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/tn/esprit/guiapplication/Ajouterprogram.fxml"));
        try {
            listView2.getScene().setRoot(fxmlLoader.load());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }




}
