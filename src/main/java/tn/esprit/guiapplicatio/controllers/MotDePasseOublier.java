package tn.esprit.guiapplicatio.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tn.esprit.guiapplicatio.services.UserService;
//import tn.esprit.guiapplicatio.services.PromoService;
import javafx.scene.control.PasswordField;
import tn.esprit.guiapplicatio.test.HelloApplication;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Random;

public class MotDePasseOublier {

    @FXML
    private PasswordField CodeRecu;

    @FXML
    private TextField EmailOublier;

    @FXML
    private Button envoyerCodeButton;
    @FXML
    void RetourMenu(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/tn/esprit/crud/LOGIN.fxml"));
        try {
            CodeRecu.getScene().setRoot(fxmlLoader.load());
        } catch (IOException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
    @FXML
    void ToChangeMdp(ActionEvent event) {
        String codeSaisi = CodeRecu.getText(); // Récupérer le code saisi par l'utilisateur
        String codeEnvoye = UserService.getCodeRecuperation(); // Récupérer le code envoyé par e-mail depuis le service

        // Vérifier si le code saisi correspond au code envoyé par e-mail
        if (codeSaisi.equals(codeEnvoye)) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/tn/esprit/crud/ChangerMotDePasse.fxml"));
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
}
