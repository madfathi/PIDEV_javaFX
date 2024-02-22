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
    void initialize() {
        ProgramService programService = new ProgramService();
        try {
            List<Program> programs = programService.recuperer();
            ObservableList<Program> observableList = FXCollections.observableList(programs);
            listView2.setItems(observableList);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

    }

    @FXML
    public void modifierProgram(ActionEvent actionEvent) throws SQLException {
        String idText = idTFmP.getText();
        String titreText = titreTFmP.getText();
        String niveauText = niveauTFmP.getText();
        String descriptionText = descriptionTFmP.getText();
        String prixText = prixTFmP.getText();

        // Vérification du champ ID
        if (idText.isEmpty()) {
            afficherMessageErreur("Veuillez saisir l'ID.");
            return;
        }
        int id_p;
        try {
            id_p = Integer.parseInt(idText);
        } catch (NumberFormatException e) {
            afficherMessageErreur("L'ID doit être un nombre entier.");
            return;
        }

        // Vérification du champ titre
        if (titreText.isEmpty()) {
            afficherMessageErreur("Veuillez saisir le titre.");
            return;
        }

        // Vérification du champ niveau
        if (niveauText.isEmpty()) {
            afficherMessageErreur("Veuillez saisir le niveau.");
            return;
        }

        // Vérification du champ description
        if (descriptionText.isEmpty()) {
            afficherMessageErreur("Veuillez saisir la description.");
            return;
        }

        // Vérification du champ prix
        if (prixText.isEmpty()) {
            afficherMessageErreur("Veuillez saisir le prix.");
            return;
        }
        int prix;
        try {
            prix = Integer.parseInt(prixText);
        } catch (NumberFormatException e) {
            afficherMessageErreur("Le prix doit être un nombre entier.");
            return;
        }

        // Si toutes les vérifications sont passées, créer un nouveau programme et le modifier
        Program po = new Program(id_p, titreText, niveauText, descriptionText, prix);
        ProgramService programService = new ProgramService();
        programService.modifier(po);

        // Afficher une confirmation
        Alert al = new Alert(Alert.AlertType.INFORMATION);
        al.setTitle("Succès");
        al.setHeaderText(null);
        al.setContentText("Program modifié avec succès.");
        al.showAndWait();

        // Réinitialiser les champs
        initialize();
    }




    public void supprimerprogram(ActionEvent actionEvent) {
        ProgramService programService = new ProgramService();
        Program program = new Program();
        program.setId_p(Integer.parseInt(idTFmP.getText()));
        try {
            programService.supprimer(program.getId_p());
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



    public void retour3(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/tn/esprit/guiapplication/Ajouterprogram.fxml"));
        try {
            listView2.getScene().setRoot(fxmlLoader.load());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
    private void afficherMessageErreur(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur de saisie");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
/*
    @FXML
    public void sortProgram(ActionEvent actionEvent) {
        ProgramService ProgramService = new ProgramService(); // Create an instance of ClientService
        List<Program> programs = null; // Call the non-static method on the instance

        programs = ProgramService.getAll();
        ProgramService.sortByProgram(programs);
        ObservableList<Program> observableList = FXCollections.observableArrayList();
        for (Program p : programs) {
            observableList.add(p);
        }
        listView2.setItems(observableList);
        initialize();

    }
*/

    @FXML

    public void trouverProgram(ActionEvent actionEvent) {
        int idProgram = Integer.parseInt(idTFmP.getText());
        ProgramService programService = new ProgramService();
        try {
            Program programTrouvee = programService.rechercherParId(idProgram);

            if (programTrouvee != null) {
                System.out.println("Programme trouvé : " + programTrouvee.getId_p());
                idTFmP.setText(String.valueOf(programTrouvee.getId_p()));
                titreTFmP.setText(programTrouvee.getTitre());
                niveauTFmP.setText(programTrouvee.getNiveau());
                descriptionTFmP.setText(programTrouvee.getDescription());
                prixTFmP.setText(String.valueOf(programTrouvee.getPrix()));
            } else {
                System.out.println("Aucun programme trouvé avec l'ID : " + idProgram);
            }
        } catch (SQLException e) {
            // Gérer l'exception SQLException
            e.printStackTrace();
        }
    }

}
