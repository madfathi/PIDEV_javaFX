package tn.esprit.guiapplicatio.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import tn.esprit.guiapplicatio.models.CodePromo;
import tn.esprit.guiapplicatio.services.PromoService;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import tn.esprit.guiapplicatio.test.HelloApplication;



public class ModifierCode {

    @FXML
    private TextField codeNouv;

    @FXML
    private DatePicker dateNouv;

    @FXML
    private TextField idCodeMod;

    @FXML
    private TextField useridNouv;

    @FXML
    private TextField montantNouv;

    private PromoService promoService;

    @FXML
    void RetourMenu(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/tn/esprit/guiapplicatio/GestionUser.fxml"));
        try {
            useridNouv.getScene().setRoot(fxmlLoader.load());
        } catch (IOException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
    @FXML
    void VersAfficher(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/tn/esprit/guiapplicatio/AfficherCode.fxml"));
        try {
            idCodeMod.getScene().setRoot(fxmlLoader.load());
        } catch (IOException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @FXML
    void VersAjouter(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/tn/esprit/guiapplicatio/AjouterCode.fxml"));
        try {
            idCodeMod.getScene().setRoot(fxmlLoader.load());
        } catch (IOException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @FXML
    void VersSupprimer(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/tn/esprit/guiapplicatio/SupprimerCode.fxml"));
        try {
            idCodeMod.getScene().setRoot(fxmlLoader.load());
        } catch (IOException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    {
        try {
            promoService = new PromoService();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void modifierCode() {
        try {
            // Récupérer les données saisies dans les champs de texte
            int id = Integer.parseInt(idCodeMod.getText());
            int nouveauCode = Integer.parseInt(codeNouv.getText());
            LocalDate nouvelleDate = dateNouv.getValue();
            int nouvelmontant = Integer.parseInt(montantNouv.getText());
            int nouvelUserId = Integer.parseInt(useridNouv.getText());


            // Créer un objet CodePromo avec les nouvelles valeurs
            CodePromo codePromo = new CodePromo(id, nouveauCode, nouvelleDate, nouvelmontant, nouvelUserId);


            // Mettre à jour le code promo dans la base de données
            promoService.modifier(codePromo);

            // Afficher un message de confirmation
            afficherMessage("Succès", "Le code promo a été modifié avec succès.");
        } catch (NumberFormatException e) {
            afficherErreur("Erreur", "Veuillez saisir des valeurs valides.");
        } catch (SQLException e) {
            afficherErreur("Erreur", "Erreur lors de la modification du code promo : " + e.getMessage());
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
    void Modification(ActionEvent event) {
        modifierCode();
    }
}
