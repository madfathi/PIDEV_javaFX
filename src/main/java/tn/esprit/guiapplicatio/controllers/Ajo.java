package tn.esprit.guiapplicatio.controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import tn.esprit.guiapplicatio.models.Seance;
import tn.esprit.guiapplicatio.services.SeanceService;
import tn.esprit.guiapplicatio.test.HelloApplication;

import java.io.*;
import java.nio.channels.FileChannel;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class Ajo {
    @FXML
    private Label nerreurLabel;
    @FXML
    private Label derreurLabel;
    @FXML
    private Label erreurLabel;


    @FXML
    private ImageView tfImage;
    String filepath = null, filename = null, fn = null;
    @FXML
    private Button btnImageC;
    @FXML
    private DatePicker date_debut;

    @FXML
    private DatePicker date_fin;
    @FXML
    private TextField dureeTF;
    FileChooser fc = new FileChooser();
    @FXML
    private TextField nbTF;

    @FXML
    private TextField typeTF;
    String uploads = "C:/Users/Lenovo/IdeaProjects/guiapplicatio/img/";
    public static String formatDuration(Duration duration) {
        long heures = duration.toHours();
        long minutes = duration.toMinutesPart();
        long secondes = duration.toSecondsPart();
        return String.format("%d heures %d minutes %d secondes", heures, minutes, secondes);
    }

    public void ajoSeance(ActionEvent actionEvent) {

        String type = typeTF.getText();
    //    String duree = dureeTF.getText();
        String nbMaximal = nbTF.getText();
        if (type.isEmpty() ||  nbMaximal.isEmpty() || date_fin.getValue()==null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);

            // Sortir de la méthode si un champ
            if (type.isEmpty()) {
                alert.setTitle("Erreur");
                alert.setContentText("champs type est vide");
                alert.showAndWait();
                return; // Sortir de la méthode si un champ
            }

            if (date_fin.getValue()==null) {
                alert.setTitle("Erreur");
                alert.setContentText("champs nombre est vide");
                alert.showAndWait();
                return; // Sortir de la méthode si un champ


            }
        }
        erreurLabel.setText("");
        nerreurLabel.setText("");
        derreurLabel.setText("");
        SeanceService cs = new SeanceService();
        Seance co = new Seance();
        co.setType_seance(typeTF.getText());
        co.setCategorie(filename);
      //  co.setDuree_seance(dureeTF.getText());
        co.setNb_maximal(Integer.parseInt(nbTF.getText()));
        co.setDate_fin(java.sql.Date.valueOf(date_fin.getValue()));
        LocalDate currentDate = LocalDate.now();
        LocalDate selectedDate = date_fin.getValue();
        LocalDateTime currentDateTime = LocalDateTime.of(currentDate, LocalTime.now());
        LocalDateTime selectedDateTime = LocalDateTime.of(selectedDate, LocalTime.MIDNIGHT);



        if (
                !typeTF.getText().matches("^[a-zA-Z]+$") ||
                        selectedDate.compareTo(currentDate) < 0 ||
                        Integer.parseInt(nbMaximal) < 1 || Integer.parseInt(nbMaximal) > 10) {



            if (!typeTF.getText().matches("^[a-zA-Z]+$")) {
                erreurLabel.setText("Le type doit contenir uniquement des lettres.");
                erreurLabel.setStyle("-fx-text-fill: red;");
            }

            if (Integer.parseInt(nbMaximal) < 1 || Integer.parseInt(nbMaximal) > 10) {
                derreurLabel.setText("La durée doit être entre 1 et 10.");
                derreurLabel.setStyle("-fx-text-fill: red;");
            }

            if (selectedDate.compareTo(currentDate) < 0) {
                nerreurLabel.setText("DATE INVALIDE");
                nerreurLabel.setStyle("-fx-text-fill: red;");
                return;
            }

        } else {
            Duration duration = Duration.between(currentDateTime, selectedDateTime);

            long days = duration.toDays();
            long hours = duration.toHours() % 24;
            long minutes = duration.toMinutes() % 60;
            long seconds = duration.getSeconds() % 60;

            String formattedDuration = String.format("%d jours, %d heures, %d minutes, %d secondes", days, hours, minutes, seconds);
            //  String formattedDuration = formatDuration(duration);
            co.setDuree_seance(formattedDuration);
            erreurLabel.setText(null);
            derreurLabel.setText(null);
            nerreurLabel.setText(null);

            try {
                co.setDate_fin(java.sql.Date.valueOf(date_fin.getValue()));
                cs.ajouter(co);
                //   addDataToChart();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Success");
                alert.setContentText("seance ajoutée");
                alert.showAndWait();

            } catch (SQLException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }

        }
    }




    public void btnImageC(ActionEvent actionEvent) throws IOException {

        File file = fc.showOpenDialog(null);
        // Shows a new file open dialog.
        if (file != null) {
            // URI that represents this abstract pathname
            tfImage.setImage(new Image(file.toURI().toString()));

            filename = file.getName();
            filepath = file.getAbsolutePath();

            fn = filename;

            FileChannel source = new FileInputStream(filepath).getChannel();
            FileChannel dest = new FileOutputStream(uploads + filename).getChannel();
            dest.transferFrom(source, 0, source.size());
        } else {
            System.out.println("Fichier invalide!");
        }
    }




    public void retour(ActionEvent actionEvent) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/tn/esprit/guiapplicatio/AjoSeance.fxml"));
        Parent root = fxmlLoader.load();
        dureeTF.getScene().setRoot(root);

    }
}
