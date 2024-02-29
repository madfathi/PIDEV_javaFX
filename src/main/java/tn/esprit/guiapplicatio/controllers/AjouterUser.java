package tn.esprit.guiapplicatio.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import tn.esprit.guiapplicatio.models.User;
import tn.esprit.guiapplicatio.services.UserService;
import tn.esprit.guiapplicatio.test.HelloApplication;

import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.sql.SQLException;

public class AjouterUser {
    @FXML
    private TextField adresseTF;

    @FXML
    private TextField emailTF;

    @FXML
    private TextField mdpTF;

    @FXML
    private TextField nomTF;

    @FXML
    private TextField prenomTF;


    @FXML
    void RetourMenu(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/tn/esprit/guiapplicatio/AjoSeance.fxml"));
        try {
            nomTF.getScene().setRoot(fxmlLoader.load());
        } catch (IOException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @FXML
    void afficherUsers(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/tn/esprit/guiapplicatio/AfficherUsers.fxml"));
        try {
            nomTF.getScene().setRoot(fxmlLoader.load());
        } catch (IOException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @FXML
    void ajouterUser(ActionEvent event) throws SQLException {
        String nom = nomTF.getText();
        String prenom = prenomTF.getText();
        String adresse = adresseTF.getText();
        String email = emailTF.getText();
        String mdp = mdpTF.getText();

        // Vérification si un champ est vide
        if (nom.isEmpty() || prenom.isEmpty() || adresse.isEmpty() || email.isEmpty() || mdp.isEmpty()) {
            afficherErreur("Veuillez remplir tous les champs.");
            return; // Arrête l'exécution de la méthode si un champ est vide
        }

        // Validation du champ nom : doit contenir uniquement des lettres
        if (!nom.matches("[a-zA-Z]+")) {
            afficherErreur("Le nom doit contenir uniquement des lettres.");
            return; // Arrête l'exécution de la méthode si le nom ne contient pas que des lettres
        }

        // Validation du champ prenom : doit être "admin" ou "user"
        if (!prenom.equals("admin") && !prenom.equals("user")) {
            afficherErreur("Le prénom doit être 'admin' ou 'user'.");
            return; // Arrête l'exécution de la méthode si le prénom n'est pas valide
        }

        // Validation du champ email : doit être une adresse email valide
        if (!email.matches("^[\\w.-]+@[\\w.-]+\\.[A-Za-z]{2,}$")) {
            afficherErreur("L'adresse email n'est pas valide.");
            return; // Arrête l'exécution de la méthode si l'email n'est pas valide
        }

        // Validation du champ mdp : doit contenir au moins une majuscule et un caractère spécial
        if (!mdp.matches("^(?=.*[A-Z])(?=.*[!@#$%^&*()-+=]).+$")) {
            afficherErreur("Le mot de passe doit contenir au moins une majuscule et un caractère spécial.");
            return; // Arrête l'exécution de la méthode si le mot de passe n'est pas valide
        }


        // Si toutes les validations sont réussies, ajoutez l'utilisateur
        UserService userService = new UserService();
        User user = new User();
        user.setNom(nom);
        user.setPrenom(prenom);
        user.setAdresse(adresse);
        user.setEmail(email);
        user.setMdp(mdp);

        try {
            userService.ajouter(user);
            afficherSucces("Personne ajoutée avec succès.");
        } catch (SQLException e) {
            afficherErreur("Erreur lors de l'ajout de la personne : " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private void afficherErreur(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void afficherSucces(String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Succès");
        alert.setContentText(message);
        alert.showAndWait();
    }







    @FXML
    void VersModifier(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/tn/esprit/guiapplicatio/ModifierUser.fxml"));
        try {
            nomTF.getScene().setRoot(fxmlLoader.load());
        } catch (IOException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @FXML
    void VersSupprimer(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/tn/esprit/guiapplicatio/SupprimerUser.fxml"));
        try {
            nomTF.getScene().setRoot(fxmlLoader.load());
        } catch (IOException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }



}