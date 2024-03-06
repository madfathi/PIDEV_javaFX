
package tn.esprit.guiapplicatio.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.pdfbox.text.PDFTextStripper;
import tn.esprit.guiapplicatio.services.ClientService;
import tn.esprit.guiapplicatio.models.Client;
import tn.esprit.guiapplicatio.test.HelloApplication;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    private Label weightLabel;

    @FXML
    private Label heightLabel;

    @FXML
    private Label ageLabel;


    @FXML
    void afficherClient(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/tn/esprit/guiapplicatio/Afficherclient.fxml"));
        try {
            ageTF.getScene().setRoot(fxmlLoader.load());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }


    }


    @FXML
    void ajouterClient(ActionEvent event) {
        ClientService cs = new ClientService();
        Client co = new Client();


        String nom = nomTF.getText().trim();
        String prenom = prenomTF.getText().trim();
        String ageStr = ageTF.getText().trim();
        String poidsStr = poidsTF.getText().trim();
        String hauteurStr = hauteurTF.getText().trim();


        if (nom.isEmpty() || prenom.isEmpty() || ageStr.isEmpty() || poidsStr.isEmpty() || hauteurStr.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText("Veuillez remplir tous les champs.");
            alert.showAndWait();
            return;
        }


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
            return;
        }


        co.setNom(nom);
        co.setPrenom(prenom);
        co.setAge(age);
        co.setPoids(poids);
        co.setHauteur(hauteur);

        if (!nom.matches("[a-zA-Z]+")) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText(null);
            alert.setContentText("Le nom de client doit contenir uniquement des lettres.");
            alert.showAndWait();
            return;
        }
        if (!prenom.matches("[a-zA-Z]+")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText(null);
            alert.setContentText("Le prenom d'utilisateur doit contenir uniquement des lettres.");
            alert.showAndWait();
            return; // Sortir de la méthode si la validation échoue
        }

        double bmr = calculateBMR(age, poids, hauteur);


        BMR.setText(String.format("BMR: %.2f", bmr));

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
            alert.setContentText("Client ajouté." + "Attendre un mail du coach");
            alert.showAndWait();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }


    private double calculateBMR(int age, int poids, int hauteur) {
        return 655 + (9.6 * poids) + (1.8 * hauteur) - (4.7 * age);
    }


    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("BMR Résultat");
        alert.setHeaderText(title);
        alert.setContentText(message);
        alert.showAndWait();
    }


    public void PdfClient(ActionEvent actionEvent) {
        String nomTf = nomTF.getText();
        String prenomTf = prenomTF.getText();
        String ageTf = ageTF.getText();
        String poidsTf = poidsTF.getText();
        String hauteurTf = hauteurTF.getText();
        String bmrTf = BMR.getText();
        try {

            PDDocument document = new PDDocument();
            PDPage page = new PDPage();
            document.addPage(page);
            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
            contentStream.beginText();
            contentStream.newLineAtOffset(100, 700);


            String ligne = "nom :" + nomTf + "  Prenom : " + prenomTf + "  Age : " + ageTf + "  Poids : " + poidsTf + "  Hauteur : " + hauteurTf + "  : " + bmrTf;
            contentStream.showText(ligne);

            contentStream.newLine();
            contentStream.newLineAtOffset(0, -15);


            contentStream.endText();


            contentStream.close();

            String outputPath = "C:/Users/Lenovo/IdeaProjects/guiapplicatio/src/PDF/seance.pdf";
            File file = new File(outputPath);
            document.save(file);


            document.close();

            System.out.println("Le PDF a été généré avec succès.");
            Desktop.getDesktop().open(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void impo(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir un fichier PDF");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichiers PDF", "*.pdf"));
        File selectedFile = fileChooser.showOpenDialog(new Stage());

        if (selectedFile != null) {
            try {
                PDDocument document = PDDocument.load(selectedFile);
                PDFTextStripper pdfStripper = new PDFTextStripper();
                String text = pdfStripper.getText(document);
                document.close();

                ageLabel.setText(extractAge(text));
                weightLabel.setText(extractWeight(text));
                heightLabel.setText(extractHeight(text));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private String extractAge(String text) {
        String agePattern = "(?i)(\\bAge\\b)\\s*:\\s*(\\d+)";

        Pattern pattern = Pattern.compile(agePattern);
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            this.ageTF.setText(matcher.group(2));
            return matcher.group(2);

        }
        return "Age non trouvé";
    }

    private String extractWeight(String text) {
        String weightPattern = "(?i)(\\bPoids\\b)\\s*:\\s*(\\d+)";
        Pattern pattern = Pattern.compile(weightPattern);
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            this.poidsTF.setText(matcher.group(2));
            return matcher.group(2);
        }
        return "Poids non trouvé";
    }

    private String extractHeight(String text) {
        String heightPattern = "(?i)(\\bHauteur\\b)\\s*:\\s*(\\d+)";
        Pattern pattern = Pattern.compile(heightPattern);
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            this.hauteurTF.setText(matcher.group(2));
            return matcher.group(2);
        }
        return "Taille non trouvée";
    }

    public void open_dashboard(MouseEvent mouseEvent) throws IOException {


        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/tn/esprit/guiapplicatio/o.fxml"));
        hauteurTF.getScene().setRoot(fxmlLoader.load());



    }
}





