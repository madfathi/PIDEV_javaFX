package tn.esprit.guiapplicatio.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tn.esprit.guiapplicatio.models.User;
import tn.esprit.guiapplicatio.services.UserService;
import tn.esprit.guiapplicatio.test.HelloApplication;

import java.io.IOException;
import java.sql.SQLException;

public class LOGIN {

    @FXML
    private TextField LogInEmail;

    @FXML
    private PasswordField LogInmdp;

    @FXML
    void verspageadus(ActionEvent event) throws SQLException {
        // Récupérer l'email et le mot de passe entrés par l'utilisateur
        String email = LogInEmail.getText();
        String mdp = LogInmdp.getText();
        System.out.println(email);
        // Vérifier si l'email et le mot de passe correspondent à l'un des Admin
        if ((email.equals("a") && mdp.equals("b")) ||
                (email.equals("maddeh.fathin@gmail.com") && mdp.equals("fathiM1@")) ||

                (email.equals("amrou.bouslimi@esprit.tn") && mdp.equals("amrouB1@")) ||
                (email.equals("salimmahdi680@gmail.com") && mdp.equals("salimM1@"))) {
            // Naviguer vers la page de l'administrateur (PageAdmin.fxml)
            navigateToPage("/tn/esprit/guiapplicatio/AjoSeance.fxml", event);
        } else {
            // Sinon, vérifier si les informations d'identification correspondent à un utilisateur dans la base de données
            UserService userService = new UserService();
            try {
                User user = userService.authentifier(email, mdp);
                if (user != null) {
                    // Naviguer vers la page utilisateur (PageUser.fxml)
                    navigateToPage("/tn/esprit/guiapplicatio/reservat.fxml", event);

                } else {
                    navigateToPage("/tn/esprit/guiapplicatio/reservat.fxml", event);
                 //   afficherErreur("Erreur", "Votre Email ou votre mot de passe est incorrect.");
                }
            } catch (SQLException e) {
                afficherErreur("Erreur", "Votre connexion est echouee veuillez ressayer encore une foix : " + e.getMessage());
                e.printStackTrace();
            }
        }
    }


    private void navigateToPage(String pagePath, ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(pagePath));
            Parent root = loader.load();
            Stage stage = (Stage) LogInEmail.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void afficherErreur(String titre, String contenu) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titre);
        alert.setContentText(contenu);
        alert.showAndWait();
    }
    public void SIGNUP(ActionEvent actionEvent) {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/tn/esprit/guiapplicatio/SIGNUP.fxml"));
        try {
            LogInmdp.getScene().setRoot(fxmlLoader.load());
        } catch (IOException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }

    }







    @FXML
    void PageOubliermdp(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/tn/esprit/guiapplicatio/MotDePasseOublier.fxml"));
        try {
            LogInmdp.getScene().setRoot(fxmlLoader.load());
        } catch (IOException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }

    }
}

