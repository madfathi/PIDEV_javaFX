package tn.esprit.guiapplicatio.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;
import tn.esprit.guiapplicatio.services.UserService;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;

public class ChangerMotDePasse {
    @FXML
    private TextField EmailOublier;
    @FXML
    private TextField CodeRecu;
    @FXML
    private PasswordField NewMdp;

    @FXML
    private PasswordField confirmernewMdp;

    @FXML
    private TextField emailModif;

    private UserService userService;

    public ChangerMotDePasse() {
        try {
            userService = new UserService();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void ModifierNewMdp(ActionEvent event) {
        String email = emailModif.getText();
        String nouveauMdp = NewMdp.getText();
        String confirmationMdp = confirmernewMdp.getText();

        if (!nouveauMdp.equals(confirmationMdp)) {
            afficherErreur("Erreur", "Les mots de passe ne correspondent pas.");
            return; // Sortir de la méthode si les mots de passe ne correspondent pas
        }

        try {
            // Vérifier si l'utilisateur existe
            if (userService.getByEmail(email) != null) {
                // Hasher le nouveau mot de passe
                String hashedPassword = BCrypt.hashpw(nouveauMdp, BCrypt.gensalt());
                // Mettre à jour le mot de passe de l'utilisateur
                userService.modifierMotDePasse(email, hashedPassword);
                afficherMessage("Succès", "Votre Mot de passe a ete mis à jour avec succès.");
            } else {
                afficherErreur("Erreur", "L Utilisateur n'existe pas.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void afficherMessage(String titre, String contenu) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(contenu);

        // Ajouter un bouton personnalisé pour naviguer vers LOGIN.fxml
        ButtonType loginButton = new ButtonType("Se connecter");
        alert.getButtonTypes().setAll(loginButton);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == loginButton) {
            // L'utilisateur a cliqué sur "Se connecter"
            URL loginURL = getClass().getResource("/tn/esprit/crud/LOGIN.fxml");
            if (loginURL != null) {
                Stage stage = (Stage) confirmernewMdp.getScene().getWindow();
                stage.close();
                try {
                    FXMLLoader loader = new FXMLLoader(loginURL);
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Erreur : Fichier LOGIN.fxml introuvable.");
            }
        }
    }

    private void afficherErreur(String titre, String contenu) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(contenu);
        alert.showAndWait();
    }

    @FXML
    void ToChangeMdp(ActionEvent event) {
        String codeSaisi = CodeRecu.getText(); // Récupérer le code saisi par l'utilisateur
        String codeEnvoye = UserService.getCodeRecuperation(); // Récupérer le code envoyé par e-mail depuis le service

        // Vérifier si le code saisi correspond au code envoyé par e-mail
        if (codeSaisi.equals(codeEnvoye)) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/tn/esprit/guiapplicatio/ChangerMotDePasse.fxml"));
                Parent root = fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();

                // Fermer la fenêtre actuelle si nécessaire
                ((Node)(event.getSource())).getScene().getWindow().hide();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Le code saisi est incorrect !");
        }
    }
    @FXML
    void envoyerCodeAction(ActionEvent event) throws SQLException {
        String email = EmailOublier.getText();
        UserService userService = new UserService();
        String codeRecuperation = userService.generateRandomCode(); // Générer un code de récupération aléatoire

        userService.envoyerEmailRecuperation(email, codeRecuperation);
        System.out.println("Votre code de récupération a ete envoyé avec succès !");
    }

    public void RetourMenu(ActionEvent actionEvent) {
    }
}
