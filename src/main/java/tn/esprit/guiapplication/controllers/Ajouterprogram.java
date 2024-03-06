package tn.esprit.guiapplication.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import tn.esprit.guiapplication.Cellule.ClientCell;
import tn.esprit.guiapplication.models.Client;
import tn.esprit.guiapplication.models.Program;
import tn.esprit.guiapplication.services.ClientService;
import tn.esprit.guiapplication.services.ProgramService;
import tn.esprit.guiapplication.test.HelloApplication;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

public class Ajouterprogram {


    @FXML
    private TextField descriptionTF;

    @FXML
    private TextField nom;

    @FXML
    private TextField niveauTF;

    @FXML
    private TextField prixTF;

    @FXML
    private TextField titreTF;

    @FXML
    private ImageView imageTF;

    private String filePath;
    @FXML
    private ListView<Client> listView;

    @FXML
    void initialize() {
        ClientService clientService = new ClientService();
        try {
            List<Client> clients = clientService.recuperer();
            listView.setCellFactory(param -> new ClientCell());
            ObservableList<Client> observableList = FXCollections.observableList(clients);

            listView.setItems(observableList);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        listView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {

                Client selectedClient = (Client) listView.getSelectionModel().getSelectedItem();

                // Afficher les informations du client dans les champs de texte
              //  nomTFm.setText(selectedClient.getNom());
              //  prenomTFm.setText(selectedClient.getPrenom());
              //  ageTFm.setText(String.valueOf(selectedClient.getAge()));
              //  poidsTFm.setText(String.valueOf(selectedClient.getPoids()));
               // hauteurTFm.setText(String.valueOf(selectedClient.getHauteur()));
            }
        });

    }
    @FXML
    void ajouterProgram(ActionEvent event) throws SQLException {
        ProgramService po = new ProgramService();
        Program ps = new Program();
        ClientService cs =new ClientService();
        Client c=new Client();
        c=cs.getClientByName(nom.getText());
        ps.setTitre(titreTF.getText());
        ps.setNiveau(niveauTF.getText());
        ps.setDescription(descriptionTF.getText());
        ps.setPrix(Integer.parseInt(prixTF.getText()));
        ps.setImage(filePath);
        ps.setClient(c);
        try {
            po.ajouter(ps);
            envoyer(nom.getText(), titreTF.getText(), niveauTF.getText(), descriptionTF.getText(), prixTF.getText() , filePath);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION) ;
            alert.setTitle("Success");
            alert.setContentText("program ajoutée");
            alert.showAndWait();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR) ;
            alert.setTitle("Erreur");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }

    }

    public void afficherProgram(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/tn/esprit/guiapplication/Afficherprogram.fxml"));
        try {
            titreTF.getScene().setRoot(fxmlLoader.load());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

    }


    public void envoyer(String a , String b, String c, String d , String f , String g) {

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        props.put("mail.smtp.ssl.ciphersuites", "TLS_AES_256_GCM_SHA384");
        props.put("mail.smtp.socketFactory.port","465");
        props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("salim.mahdi@esprit.tn", "msty jcvl nxpz zfci");
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("salim.mahdi@esprit.tn"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("salim.mahdi@esprit.tn"));
            message.setSubject("welcome to gestion de coaching");
            message.setText("Nom: " + a  + " Titre: " + b + " Niveau: " + c + " Description: " + d + " Prix: " + f + " Image: " + g);


            session.setDebug(true);

            Transport.send(message);
            System.out.println("Message envoyé avec succès");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void handleUploadButtonAction(ActionEvent actionEvent) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image File");


        FileChooser.ExtensionFilter imageFilter =
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif");
        fileChooser.getExtensionFilters().add(imageFilter);


        File selectedFile = fileChooser.showOpenDialog(new Stage());


        if (selectedFile == null || !isImageFile(selectedFile)) {

            imageTF.setImage(null);
            System.out.println("Please select a valid image file.");
            return;
        }


        filePath = selectedFile.getAbsolutePath().replace("\\", "/");
        System.out.println("File path stored: " + filePath);

        javafx.scene.image.Image image = new javafx.scene.image.Image(selectedFile.toURI().toString());
        imageTF.setImage(image);
    }

    private boolean isImageFile(File file) {
        try {
            javafx.scene.image.Image image = new javafx.scene.image.Image(file.toURI().toString());
            return !image.isError();
        } catch (Exception e) {
            return false;
        }
    }

    public void statistiques(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/tn/esprit/guiapplication/Statistiques.fxml"));
        try {
            titreTF.getScene().setRoot(fxmlLoader.load());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }



    }

    public void totals(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/tn/esprit/guiapplication/stat.fxml"));
        try {
            titreTF.getScene().setRoot(fxmlLoader.load());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void afficherClient(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/tn/esprit/guiapplication/Afficherclient.fxml"));
        try {
            titreTF.getScene().setRoot(fxmlLoader.load());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}

