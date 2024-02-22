package tn.esprit.projet3a.controllers;

import com.sun.javafx.fxml.builder.JavaFXSceneBuilder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import tn.esprit.projet3a.models.Evenment;
import tn.esprit.projet3a.services.EvenmentService;
import tn.esprit.projet3a.test.HelloApplication;


import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class AjouterEvenment {
    @FXML
    private DatePicker date_eventTF;

    @FXML
    private TextField lieu_eventTF;


    @FXML
    private Label nom_eventEror;

    @FXML
    private TextField nom_eventTF;

    @FXML
    private TextField nom_starTF;

    @FXML
    private Label nom_starEror;

    @FXML
    private Label lieu_eventEror;

    @FXML
    private ImageView imageTF;

    @FXML
    private Label image_error;

    private String filePath;



    void clearFields() {
        nom_eventTF.clear();
        date_eventTF.setValue(null);
        lieu_eventTF.clear();
        nom_starTF.clear();
    }


    @FXML
    void afficherEvenment(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/AfficherEvenment.fxml"));
        try {
            nom_eventTF.getScene().setRoot(fxmlLoader.load());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

    }



    @FXML
    public void handleUploadButtonAction(ActionEvent actionEvent) {
        // Create a file chooser
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image File");

        // Set file extension filter to only allow image files
        FileChooser.ExtensionFilter imageFilter =
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif");
        fileChooser.getExtensionFilters().add(imageFilter);

        // Show open file dialog
        File selectedFile = fileChooser.showOpenDialog(new Stage());

        // Check if a file is selected and it's an image
        if (selectedFile != null && isImageFile(selectedFile)) {
            // Store the file path with forward slashes
            filePath = selectedFile.getAbsolutePath().replace("\\", "/");
            System.out.println("File path stored: " + filePath);

            // Set the image in the ImageView
            javafx.scene.image.Image image = new javafx.scene.image.Image(selectedFile.toURI().toString());
            imageTF.setImage(image);
        } else {
            System.out.println("Please select a valid image file.");
        }
    }


    // Method to check if the selected file is an image file
    private boolean isImageFile(File file) {
        try {
            javafx.scene.image.Image image = new Image(file.toURI().toString());
            return image.isError() ? false : true;
        } catch (Exception e) {
            return false;
        }
    }

    // Method to retrieve the stored file path
    public String getFilePath() {
        return filePath;
    }

    @FXML
    void ajouterEvenment(ActionEvent event) {
        EvenmentService evenmentService = new EvenmentService();
        Evenment evenment = new Evenment();

        // Get the text from nom_eventTF
        String nomEventText = nom_eventTF.getText();

        // Get the text from lieu_eventTF
        String lieuEventText = lieu_eventTF.getText();

        // Get the text from nom_starTF
        String nomStarText = nom_starTF.getText();

        // Get the file path from the ImageView
        String path = getFilePath();

        // Clear previous error messages
        clearErrorLabels();

        boolean valid = true;

        // Check if the text is empty for nom_event
        if (nomEventText.isEmpty()) {
            nom_eventEror.setText("Le champ 'nom event' ne doit pas être vide.");
            nom_eventEror.setStyle("-fx-text-fill: red;");
            valid = false;
        }
        // Check if the text contains only letters and numbers for nom_event
        else if (!nomEventText.matches("[a-zA-Z0-9 ]+")) {
            nom_eventEror.setText("Le champ 'nom event' ne doit contenir que des lettres et des chiffres.");
            nom_eventEror.setStyle("-fx-text-fill: red;");
            valid = false;
        }

        // Check if the text is empty for lieu_event
        if (lieuEventText.isEmpty()) {
            lieu_eventEror.setText("Le champ 'lieu event' ne doit pas être vide.");
            lieu_eventEror.setStyle("-fx-text-fill: red;");
            valid = false;
        }
        // Check if the text contains only letters and numbers for lieu_event
        else if (!lieuEventText.matches("[a-zA-Z0-9 ]+")) {
            lieu_eventEror.setText("Le champ 'lieu event' ne doit contenir que des lettres et des chiffres.");
            lieu_eventEror.setStyle("-fx-text-fill: red;");
            valid = false;
        }

        // Check if the text is empty for nom_star
        if (nomStarText.isEmpty()) {
            nom_starEror.setText("Le champ 'nom star' ne doit pas être vide.");
            nom_starEror.setStyle("-fx-text-fill: red;");
            valid = false;
        }
        // Check if the text contains only letters and numbers for nom_star
        else if (!nomStarText.matches("[a-zA-Z0-9 ]+")) {
            nom_starEror.setText("Le champ 'nom star' ne doit contenir que des lettres et des chiffres.");
            nom_starEror.setStyle("-fx-text-fill: red;");
            valid = false;
        }

        // Check if the image path is empty
        if (path == null || path.isEmpty()) {
            // Show error message for missing image
            image_error.setText("Veuillez sélectionner une image.");
            image_error.setStyle("-fx-text-fill: red;");
            valid = false;
        }

        if (valid) {
            // If all fields are valid, set them to the evenment object
            evenment.setNom_event(nomEventText);
            evenment.setLieu_event(lieuEventText);
            evenment.setNom_star(nomStarText);

            // Proceed with other fields
            evenment.setDate_event(java.sql.Date.valueOf(date_eventTF.getValue()));
            evenment.setImage(path);
            try {
                evenmentService.ajouter(evenment);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setContentText("Evenment ajouté avec succès");
                alert.showAndWait();
                clearFields();
                imageTF.setImage(null);
            } catch (SQLException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
        }
    }


    void clearErrorLabels() {
        nom_eventEror.setText("");
        lieu_eventEror.setText("");
        nom_starEror.setText("");
    }





    @FXML
    void returnA(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/AdminEvenment.fxml"));
        try {
            nom_eventTF.getScene().setRoot(fxmlLoader.load());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

    }
}
