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
import tn.esprit.guiapplicatio.models.User;
import tn.esprit.guiapplicatio.services.UserService;
import org.mindrot.jbcrypt.BCrypt;
import tn.esprit.guiapplicatio.test.HelloApplication;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

public class SIGNUP {

    @FXML
    private TextField nomSignUp;

    @FXML
    private TextField prenomSignUp;

    @FXML
    private TextField adresseSignUp;

    @FXML
    private TextField emailSignUp;

    @FXML
    private PasswordField mdpSignUp;

    @FXML
    private PasswordField confirmeMdpSignUp;
    private String username = "fathimaddeh88@gmail.com";
    private String password = "wxnfnrqwjjcjzjby";

    @FXML
    void RetourMenu(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/tn/esprit/guiapplicatio/LOGIN.fxml"));
        try {
            mdpSignUp.getScene().setRoot(fxmlLoader.load());
        } catch (IOException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }


    @FXML
    void SignUp(ActionEvent event) {
        // Récupérer les informations saisies par l'utilisateur
        String nom = nomSignUp.getText();
        String prenom = prenomSignUp.getText();
        String adresse = adresseSignUp.getText();
        String email = emailSignUp.getText();
        String mdp = mdpSignUp.getText();
        String confirmeMdp = confirmeMdpSignUp.getText();

        // Vérification du format du nom : doit contenir uniquement des lettres
        if (!nom.matches("[a-zA-Z]+")) {
            afficherErreur("Erreur", "Le nom doit contenir uniquement des lettres.");
            return;
        }

        // Vérification du format du prénom : doit contenir uniquement des lettres
        if (!prenom.matches("[a-zA-Z]+")) {
            afficherErreur("Erreur", "Le prénom doit contenir uniquement des lettres.");
            return;
        }

        // Vérification du format de l'adresse email
        if (!email.matches("^[\\w.-]+@[\\w.-]+\\.[A-Za-z]{2,}$")) {
            afficherErreur("Erreur", "L'adresse email n'est pas valide.");
            return;
        }

        // Vérification du format du mot de passe : doit contenir au moins une majuscule, un chiffre et un caractère spécial
        if (!mdp.matches("^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*()-+=]).+$")) {
            afficherErreur("Erreur", "Le mot de passe doit contenir au moins une majuscule, un chiffre et un caractère spécial.");
            return;
        }
        // Vérifier si les mots de passe correspondent
        if (!mdp.equals(confirmeMdp)) {
            afficherErreur("Erreur", "Les mots de passe ne correspondent pas.");
            return;
        }

        // Hashage du mot de passe avant de l'ajouter à la base de données
        String mdpHash = BCrypt.hashpw(mdp, BCrypt.gensalt());

        // Créer un nouvel utilisateur avec les informations saisies
        User user = new User();
        user.setNom(nom);
        user.setPrenom(prenom);
        user.setAdresse(adresse);
        user.setEmail(email);
        user.setMdp(mdpHash);

        // Instancier le service UserService pour ajouter l'utilisateur à la base de données
        UserService userService = null;
        try {
            userService = new UserService();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            // Ajouter l'utilisateur à la base de données
            userService.ajouter(user);
            envoyer(email);

         //   userService.envoyerCode( email,  "");
            // Afficher une alerte de succès
            afficherMessageAvecBouton("Succès", "Utilisateur ajouté avec succès. Cliquez sur 'Se connecter' pour vous connecter.", event);
        } catch (SQLException e) {
            // En cas d'erreur, afficher une alerte d'erreur
            afficherErreur("Erreur", "Erreur lors de l'ajout de l'utilisateur : " + e.getMessage());
        }
    }

    private void afficherMessageAvecBouton(String titre, String contenu, ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titre);
        alert.setContentText(contenu);

        // Ajouter un bouton "Se connecter" à l'alerte
        ButtonType loginButton = new ButtonType("Se connecter");
        alert.getButtonTypes().setAll(loginButton);

        // Attendre la réponse de l'utilisateur
        alert.showAndWait().ifPresent(response -> {
            if (response == loginButton) {
                // Rediriger l'utilisateur vers la page de connexion
                redirectToLoginPage(event);
            }
        });
    }
    public void envoyer(String b) {
// Etape 1 : Création de la session
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        props.put("mail.smtp.ssl.ciphersuites", "TLS_AES_256_GCM_SHA384");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("fathimaddeh88@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(b));
            message.setSubject("");
            message.setText("votre compte a ete cree");

            // Enable debugging
            session.setDebug(true);

            Transport.send(message);
            System.out.println("Message envoyé avec succès");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    private void redirectToLoginPage(ActionEvent event) {
        // Récupérer la scène actuelle
        Scene scene = ((Node) event.getSource()).getScene();
        // Récupérer la fenêtre actuelle
        Stage stage = (Stage) scene.getWindow();

        // Charger la vue de la page de connexion
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/tn/esprit/guiapplicatio/LOGIN.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Créer une nouvelle scène avec la vue de la page de connexion
        Scene loginScene = new Scene(root);

        // Définir la nouvelle scène sur la fenêtre
        stage.setScene(loginScene);
        // Afficher la fenêtre
        stage.show();
    }

    private void afficherErreur(String titre, String contenu) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titre);
        alert.setContentText(contenu);
        alert.showAndWait();
    }


}
