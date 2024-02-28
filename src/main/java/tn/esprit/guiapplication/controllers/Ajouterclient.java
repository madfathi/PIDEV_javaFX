
package tn.esprit.guiapplication.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import tn.esprit.guiapplication.services.ClientService;
import tn.esprit.guiapplication.models.Client;
import tn.esprit.guiapplication.test.HelloApplication;

import java.io.IOException;
import java.sql.SQLException;

public class Ajouterclient {
    @FXML
    private TextField ageTF;

    @FXML
    private TextField nomTF;

    @FXML
    private TextField poidsTF;

    @FXML
    private TextField hauteurTF;

    @FXML
    private TextField prenomTF;

    @FXML
    private Label BMR;


    @FXML
    void afficherClient(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/tn/esprit/guiapplication/Afficherclient.fxml"));
        try {
            ageTF.getScene().setRoot(fxmlLoader.load());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }


    }

    /*

        @FXML
        void ajouterClient(ActionEvent event) {
            ClientService cs = new ClientService();
            Client co = new Client();
            co.setNom(nomTF.getText());
            co.setPrenom(prenomTF.getText());
            co.setAge(Integer.parseInt(ageTF.getText()));
            co.setPoids(Integer.parseInt(poidsTF.getText()));
            try {
                cs.ajouter(co);
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
    */
   /* @FXML
    void ajouterClient(ActionEvent event) {
        ClientService cs = new ClientService();
        Client co = new Client();

        // Récupérer les valeurs des champs et les trimmer pour enlever les espaces inutiles
        String nom = nomTF.getText().trim();
        String prenom = prenomTF.getText().trim();
        String ageStr = ageTF.getText().trim();
        String poidsStr = poidsTF.getText().trim();
        String hauteurStr = hauteurTF.getText().trim();

        // Vérifier si les champs sont vides
        if (nom.isEmpty() || prenom.isEmpty() || ageStr.isEmpty() || poidsStr.isEmpty() || hauteurStr.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText("Veuillez remplir tous les champs.");
            alert.showAndWait();
            return; // Sortir de la méthode si un champ est vide
        }

        // Vérifier si les champs age et poids contiennent des entiers valides
        int age, poids, hauteur;
        try {
            age = Integer.parseInt(ageStr);
            poids = Integer.parseInt(poidsStr);
            hauteur = Integer.parseInt(hauteurStr);
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText("Veuillez saisir des valeurs numériques pour l'âge et le poids.");
            alert.showAndWait();
            return; // Sortir de la méthode si une valeur numérique est invalide
        }

        // Valider les contraintes supplémentaires, par exemple la longueur du nom et du prénom

        // Ajouter le client seulement si toutes les vérifications sont passées
        co.setNom(nom);
        co.setPrenom(prenom);
        co.setAge(age);
        co.setPoids(poids);
        co.setHauteur(hauteur);

        if (!nom.matches("[a-zA-Z]+")) {
            // Afficher une alerte
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText(null);
            alert.setContentText("Le nom de client doit contenir uniquement des lettres.");
            alert.showAndWait();
            return; // Sortir de la méthode si la validation échoue
        }
            if (!prenom.matches("[a-zA-Z]+")) {
                // Afficher une alerte
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de saisie");
                alert.setHeaderText(null);
                alert.setContentText("Le prenom d'utilisateur doit contenir uniquement des lettres.");
                alert.showAndWait();
                return; // Sortir de la méthode si la validation échoue
            }
            try {
                cs.ajouter(co);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Succès");
                alert.setContentText("Client ajouté");
                alert.showAndWait();
            } catch (SQLException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
        }

*/
    @FXML
    void ajouterClient(ActionEvent event) {
        ClientService cs = new ClientService();
        Client co = new Client();

        // Récupérer les valeurs des champs et les trimmer pour enlever les espaces inutiles
        String nom = nomTF.getText().trim();
        String prenom = prenomTF.getText().trim();
        String ageStr = ageTF.getText().trim();
        String poidsStr = poidsTF.getText().trim();
        String hauteurStr = hauteurTF.getText().trim();

        // Vérifier si les champs sont vides
        if (nom.isEmpty() || prenom.isEmpty() || ageStr.isEmpty() || poidsStr.isEmpty() || hauteurStr.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText("Veuillez remplir tous les champs.");
            alert.showAndWait();
            return; // Sortir de la méthode si un champ est vide
        }

        // Vérifier si les champs age et poids contiennent des entiers valides
        int age, poids, hauteur;
        try {
            age = Integer.parseInt(ageStr);
            poids = Integer.parseInt(poidsStr);
            hauteur = Integer.parseInt(hauteurStr);
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText("Veuillez saisir des valeurs numériques pour l'âge et le poids.");
            alert.showAndWait();
            return; // Sortir de la méthode si une valeur numérique est invalide
        }

        // Valider les contraintes supplémentaires, par exemple la longueur du nom et du prénom

        // Ajouter le client seulement si toutes les vérifications sont passées
        co.setNom(nom);
        co.setPrenom(prenom);
        co.setAge(age);
        co.setPoids(poids);
        co.setHauteur(hauteur);

        if (!nom.matches("[a-zA-Z]+")) {
            // Afficher une alerte
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText(null);
            alert.setContentText("Le nom de client doit contenir uniquement des lettres.");
            alert.showAndWait();
            return; // Sortir de la méthode si la validation échoue
        }
        if (!prenom.matches("[a-zA-Z]+")) {
            // Afficher une alerte
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText(null);
            alert.setContentText("Le prenom d'utilisateur doit contenir uniquement des lettres.");
            alert.showAndWait();
            return; // Sortir de la méthode si la validation échoue
        }
// Calculate BMR
        double bmr = calculateBMR(age, poids, hauteur);

        // Display BMR in label
        BMR.setText(String.format("BMR: %.2f", bmr));

        // Display BMR Alert based on result
        if (bmr < 1500) {
            BMR.setText(BMR.getText() + " - Sous-alimenté");
        } else if (bmr >= 1500 && bmr < 2000) {
            BMR.setText(BMR.getText() + " - Normal");
        } else {
            BMR.setText(BMR.getText() + " - En surpoids");
        }
        try {
            cs.ajouter(co);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Succès");
            alert.setContentText("Client ajouté");
            alert.showAndWait();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    // Méthode pour calculer le BMR
    private double calculateBMR(int age, int poids, int hauteur) {
        // Calcul du BMR selon une formule quelconque, par exemple la formule de Harris-Benedict
        // Vous pouvez remplacer cette formule par celle que vous souhaitez utiliser
        return 655 + (9.6 * poids) + (1.8 * hauteur) - (4.7 * age);
    }

    // Méthode pour afficher une alerte
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("BMR Résultat");
        alert.setHeaderText(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    public void ajouterProgram(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/tn/esprit/guiapplication/Ajouterprogram.fxml"));
        try {
            ageTF.getScene().setRoot(fxmlLoader.load());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

    }
}





