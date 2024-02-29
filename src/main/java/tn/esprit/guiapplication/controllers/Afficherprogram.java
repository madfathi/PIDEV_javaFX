package tn.esprit.guiapplication.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import tn.esprit.guiapplication.Cellule.ClientCell;
import tn.esprit.guiapplication.Cellule.ProgramCell;
import tn.esprit.guiapplication.models.Client;
import tn.esprit.guiapplication.models.Program;
import tn.esprit.guiapplication.services.ClientService;
import tn.esprit.guiapplication.services.ProgramService;
import tn.esprit.guiapplication.test.HelloApplication;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.List;





public class Afficherprogram {

    @FXML
    private TextField descriptionTFmP;

    @FXML
    private TextField idTFmP;

    @FXML
    private ListView<Program> listView2;

    @FXML
    private TextField niveauTFmP;

    @FXML
    private TextField prixTFmP;

    @FXML
    private TextField titreTFmP;
    @FXML
    private TextField nom;

   /* @FXML
    void initialize() {
        displayEventData();
    }


    public static class ColumnListViewCell extends ListCell<String> {

        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);

            if (empty || item == null) {
                setText(null);
                setGraphic(null);
            } else {
                // Split the data into columns
                String[] columns = item.split("\\s+");

                // Create a HBox to hold the columns
                HBox rowBox = new HBox();

                // Add labels for each column (except the image path)
                for (int i = 0; i < columns.length - 1; i++) {
                    Label label = new Label(columns[i]);
                    label.setPrefWidth(150); // Set the width as needed
                    rowBox.getChildren().add(label);
                }

                // Create an ImageView for the image
                ImageView imageView = new ImageView();

                // Obtain the image path
                String imagePath = columns[columns.length - 1];

                // Construct the file URL using the correct format
                String fileUrl = "file:///" + imagePath.replace("\\", "/");

                // Load the image if the file path is valid
                try {
                    File file = new File(new URI(fileUrl));
                    if (file.exists()) {
                        Image image = new Image(file.toURI().toString());
                        imageView.setImage(image);
                        imageView.setFitWidth(100); // Adjust width of the image view
                        imageView.setPreserveRatio(true);
                        rowBox.getChildren().add(imageView);
                    } else {
                        System.out.println("Image file not found: " + fileUrl);
                    }
                } catch (URISyntaxException e) {
                    System.out.println("Invalid file URL: " + fileUrl);
                }

                // Set the HBox as the cell's graphic
                setGraphic(rowBox);
            }
        }
    }


    private void displayEventData() {
        ProgramService programService = new ProgramService();
        try {
            List<Program> programs = programService.recuperer();

            // Create an ObservableList to store the event data
            ObservableList<String> eventDataList = FXCollections.observableArrayList();

            // Add column titles
            String columnTitles = String.format("%-20s %-20s %-20s %-20s %-20s", "Date", "Lieu", "Nom", "Star", "Image Path");
            eventDataList.add(columnTitles);

            // Iterate through the list of events and add their details to the eventDataList
            for (Program program : programs) {
                String eventData = String.format("%-20s %-20s %-20s %-20s %-20s",
                        program.getTitre(),
                        program.getNiveau(),
                        program.getDescription(),
                        program.getPrix(),
                        program.getImage());
                eventDataList.add(eventData);
            }

            // Set items for the listview2 ListView
            listView2.setItems(eventDataList);

            // Set custom ListCell to format the data in columns
            listView2.setCellFactory(listView -> new ColumnListViewCell());

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

*/


    @FXML
    void initialize() {
        // Initialisation du service de programme et de la liste
        ProgramService programService = new ProgramService();
        try {
            List<Program> programs = programService.recuperer();
            listView2.setCellFactory(param -> new ProgramCell());
            ObservableList<Program> observableList = FXCollections.observableList(programs);
            listView2.setItems(observableList);

            // Gestionnaire d'événements pour la sélection d'un élément dans la liste
            listView2.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1) { // Vérifie un simple clic
                    Program selectedProgram = listView2.getSelectionModel().getSelectedItem();
                    if (selectedProgram != null) {
                        // Remplir les champs de texte avec les coordonnées du programme sélectionné
                        nom.setText(String.valueOf(selectedProgram.getClient().getNom()));
                        titreTFmP.setText(selectedProgram.getTitre());
                        niveauTFmP.setText(selectedProgram.getNiveau());
                        descriptionTFmP.setText(selectedProgram.getDescription());
                        prixTFmP.setText(String.valueOf(selectedProgram.getPrix()));
                    }
                }
            });
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    /*

    @FXML
    void initialize() {
        // Initialisation du service de programme et de la liste
        ProgramService programService = new ProgramService();
        try {
            List<Program> programs = programService.recuperer();
            listView2.setCellFactory(param -> new ListCell<Program>() {
                private ImageView imageView = new ImageView();

                @Override
                protected void updateItem(Program program, boolean empty) {
                    super.updateItem(program, empty);

                    if (empty || program == null) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        setText(program.getTitre()); // Or any other text you want to display

                        // Load image from database and convert to JavaFX Image
                        byte[] imageData = program.getImage(); // Assuming getImage() returns byte array from the database
                        ByteArrayInputStream bis = new ByteArrayInputStream(imageData);
                        Image image = new Image(bis);
                        imageView.setImage(image);
                        imageView.setFitWidth(50); // Set width as per your requirement
                        imageView.setPreserveRatio(true);

                        setGraphic(imageView);
                    }
                }
            });

            ObservableList<Program> observableList = FXCollections.observableList(programs);
            listView2.setItems(observableList);

            // Gestionnaire d'événements pour la sélection d'un élément dans la liste
            listView2.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1) { // Vérifie un simple clic
                    Program selectedProgram = listView2.getSelectionModel().getSelectedItem();
                    if (selectedProgram != null) {
                        // Remplir les champs de texte avec les coordonnées du programme sélectionné
                        nom.setText(String.valueOf(selectedProgram.getClient().getNom()));
                        titreTFmP.setText(selectedProgram.getTitre());
                        niveauTFmP.setText(selectedProgram.getNiveau());
                        descriptionTFmP.setText(selectedProgram.getDescription());
                        prixTFmP.setText(String.valueOf(selectedProgram.getPrix()));
                    }
                }
            });
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

*/

    /*@FXML
    public void modifierProgram(ActionEvent actionEvent) throws SQLException {
        String idText = idTFmP.getText();
        String titreText = titreTFmP.getText();
        String niveauText = niveauTFmP.getText();
        String descriptionText = descriptionTFmP.getText();
        String prixText = prixTFmP.getText();

        // Vérification du champ ID
        if (idText.isEmpty()) {
            afficherMessageErreur("Veuillez saisir l'ID.");
            return;
        }
        int id_p;
        try {
            id_p = Integer.parseInt(idText);
        } catch (NumberFormatException e) {
            afficherMessageErreur("L'ID doit être un nombre entier.");
            return;
        }

        // Vérification du champ titre
        if (titreText.isEmpty()) {
            afficherMessageErreur("Veuillez saisir le titre.");
            return;
        }

        // Vérification du champ niveau
        if (niveauText.isEmpty()) {
            afficherMessageErreur("Veuillez saisir le niveau.");
            return;
        }

        // Vérification du champ description
        if (descriptionText.isEmpty()) {
            afficherMessageErreur("Veuillez saisir la description.");
            return;
        }

        // Vérification du champ prix
        if (prixText.isEmpty()) {
            afficherMessageErreur("Veuillez saisir le prix.");
            return;
        }
        int prix;
        try {
            prix = Integer.parseInt(prixText);
        } catch (NumberFormatException e) {
            afficherMessageErreur("Le prix doit être un nombre entier.");
            return;
        }

        // Si toutes les vérifications sont passées, créer un nouveau programme et le modifier
        Program po = new Program(id_p, titreText, niveauText, descriptionText, prix);
        ProgramService programService = new ProgramService();
        programService.modifier(po);

        // Afficher une confirmation
        Alert al = new Alert(Alert.AlertType.INFORMATION);
        al.setTitle("Succès");
        al.setHeaderText(null);
        al.setContentText("Program modifié avec succès.");
        al.showAndWait();

        // Réinitialiser les champs
        initialize();
    }*/
    @FXML
    void modifierProgram(ActionEvent event) {
        ProgramService programService = new ProgramService();
        Program program = new Program();
        Client c = new Client();
        ClientService cs = new ClientService();
        try {
            c = cs.getClientByName(nom.getText());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Set the ID of the product
       program.setId_p(listView2.getSelectionModel().getSelectedItem().getId_p());

        program.setTitre(titreTFmP.getText());
        program.setClient(c);
        program.setNiveau(niveauTFmP.getText());
        program.setDescription(descriptionTFmP.getText());
        program.setPrix(Integer.parseInt(prixTFmP.getText()));

        try {
            programService.modifier(program);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Succes");
            alert.setContentText("Programme modifié");
            alert.showAndWait();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
        initialize();
    }




    public void supprimerprogram(ActionEvent actionEvent) {
        ProgramService programService = new ProgramService();
        Program program = new Program();
        program.setTitre(titreTFmP.getText());
        try {
            programService.supprimer(program.getTitre());
            Alert alert =new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Succes");
            alert.setContentText("Program supprimée");
            alert.showAndWait();
            initialize();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText(e.getMessage());
            alert.showAndWait();

        }
    }
    @FXML
    void clearFields(ActionEvent event) {
        titreTFmP.clear();
        prixTFmP.clear();
        niveauTFmP.clear();
        descriptionTFmP.clear();
        nom.clear();

    }



    public void retour3(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/tn/esprit/guiapplication/Ajouterprogram.fxml"));
        try {
            listView2.getScene().setRoot(fxmlLoader.load());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
    private void afficherMessageErreur(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur de saisie");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
/*
    @FXML
    public void sortProgram(ActionEvent actionEvent) {
        ProgramService ProgramService = new ProgramService(); // Create an instance of ClientService
        List<Program> programs = null; // Call the non-static method on the instance

        programs = ProgramService.getAll();
        ProgramService.sortByProgram(programs);
        ObservableList<Program> observableList = FXCollections.observableArrayList();
        for (Program p : programs) {
            observableList.add(p);
        }
        listView2.setItems(observableList);
        initialize();

    }
*/

    @FXML

    public void trouverProgram(ActionEvent actionEvent) {
        int idProgram = Integer.parseInt(idTFmP.getText());
        ProgramService programService = new ProgramService();
        try {
            Program programTrouvee = programService.rechercherParId(idProgram);

            if (programTrouvee != null) {
                System.out.println("Programme trouvé : " + programTrouvee.getId_p());
                idTFmP.setText(String.valueOf(programTrouvee.getId_p()));
                titreTFmP.setText(programTrouvee.getTitre());
                niveauTFmP.setText(programTrouvee.getNiveau());
                descriptionTFmP.setText(programTrouvee.getDescription());
                prixTFmP.setText(String.valueOf(programTrouvee.getPrix()));
            } else {
                System.out.println("Aucun programme trouvé avec l'ID : " + idProgram);
            }
        } catch (SQLException e) {
            // Gérer l'exception SQLException
            e.printStackTrace();
        }
    }

}
