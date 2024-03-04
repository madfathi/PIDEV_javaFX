package tn.esprit.guiapplicatio.controllers;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import tn.esprit.guiapplicatio.models.CodePromo;
import tn.esprit.guiapplicatio.services.PromoService;
import tn.esprit.guiapplicatio.test.HelloApplication;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneOffset;


public class AjouterCode {

    @FXML
    private TextField codeTF;

    @FXML
    private DatePicker date_expTF;

    @FXML
    private TextField user_idTF;

    @FXML
    private TextField montantTF;




    @FXML
    void RetourMenu(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/tn/esprit/guiapplicatio/GestionUser.fxml"));
        try {
            codeTF.getScene().setRoot(fxmlLoader.load());
        } catch (IOException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @FXML
    void initialize() {
        date_expTF.setValue(LocalDate.now());
    }


    @FXML
    void ajouterCode(ActionEvent event) throws SQLException {
        // Vérifier si tous les champs sont remplis
        if (codeTF.getText().isEmpty() || date_expTF.getValue() == null || user_idTF.getText().isEmpty() || montantTF.getText().isEmpty()) {
            afficherErreur("Erreur", "Tous les champs sont obligatoires.");
            return;
        }

        // Vérifier si le code promo est unique
        PromoService promoService = null;
        try {
            promoService = new PromoService();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        int code = Integer.parseInt(codeTF.getText());
        if (promoService.codeExisteDeja(code)) {
            afficherErreur("Erreur", "Ce code promo existe déjà. Veuillez saisir un code unique.");
            return;
        }

        // Vérifier si les valeurs saisies sont valides
        try {
            LocalDate date_exp = date_expTF.getValue();
            int user_id = Integer.parseInt(user_idTF.getText());
            int montant = Integer.parseInt(montantTF.getText());

            // Vérifier si le montant est dans la plage valide (minimum 10, maximum 30)
            if (montant < 10 || montant > 30) {
                afficherErreur("Erreur", "Le montant doit être compris entre 10 et 30.");
                return;
            }

            // Ajouter le code promo
            CodePromo codePromo = new CodePromo();
            codePromo.setCode(code);
            codePromo.setDate_exp(java.sql.Date.valueOf(date_exp));
            codePromo.setUser_id(user_id);
            codePromo.setmontant(montant);

            promoService.ajouter(codePromo);
            afficherMessage("Succès", "Code promo ajouté avec succès.");
        } catch (NumberFormatException e) {
            afficherErreur("Erreur", "Veuillez saisir des valeurs numériques valides pour le code et l'utilisateur.");
        } catch (SQLException e) {
            afficherErreur("Erreur", "Erreur lors de l'ajout du code promo : " + e.getMessage());
            e.printStackTrace(); // Affichez la pile d'erreur pour un débogage approfondi
        }
    }



    private void afficherMessage(String titre, String contenu) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titre);
        alert.setContentText(contenu);
        alert.showAndWait();
    }

    private void afficherErreur(String titre, String contenu) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titre);
        alert.setContentText(contenu);
        alert.showAndWait();
    }
    @FXML
    void VersAfficher(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/tn/esprit/crud/AfficherCode.fxml"));
        try {
            codeTF.getScene().setRoot(fxmlLoader.load());
        } catch (IOException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }

    }

    @FXML
    void VersModifier(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/tn/esprit/guiapplicatio/ModifierCode.fxml"));
        try {
            codeTF.getScene().setRoot(fxmlLoader.load());
        } catch (IOException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @FXML
    void VersSupprimer(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/tn/esprit/guiapplicatio/SupprimerCode.fxml"));
        try {
            codeTF.getScene().setRoot(fxmlLoader.load());
        } catch (IOException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
