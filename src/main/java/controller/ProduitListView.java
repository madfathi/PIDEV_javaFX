package controller;

import entities.Categorie;
import entities.Produit;
import javafx.collections.FXCollections;
import java.io.InputStream;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import service.ProduitService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class ProduitListView {
    @FXML
    private ListView<String> produitlistview;
    @FXML
    private Button sendpdf;



    @FXML
    void initialize() {
        displayEventData();
    }

    public void setItems(ObservableList<Categorie> categories) {
    }

    public void backProduit(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/AjouterProduit.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        Node source = (Node) actionEvent.getSource();
        Stage currentStage = (Stage) source.getScene().getWindow();
        currentStage.close();
        stage.show();
    }


    public static class ColumnListViewCell extends ListCell<String> {

        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);

            if (empty || item == null) {
                setText(null);
                setGraphic(null); // Clear graphic if cell is empty
            } else {
                // Split the data into columns
                String[] columns = item.split("\\s{2,}"); // Split by 2 or more spaces

                // Create a HBox to hold the columns
                HBox rowBox = new HBox();

                // Add labels for each column except the last one (image path)
                for (int i = 0; i < columns.length - 1; i++) {
                    Label label = new Label(columns[i]);
                    label.setPrefWidth(150); // Set the width as needed
                    rowBox.getChildren().add(label);
                }

                // Add the image view for the last column (image path)
                ImageView imageView = new ImageView();
                String imagePath = columns[columns.length - 1];
                try {
                    // Load image from classpath resources
                    InputStream stream = getClass().getResourceAsStream("/Photos/" + imagePath);
                    if (stream != null) {
                        Image image = new Image(stream);
                        imageView.setImage(image);
                        imageView.setFitWidth(100); // Adjust width of the image view
                        imageView.setPreserveRatio(true);
                        rowBox.getChildren().add(imageView);
                    } else {
                        System.err.println("Image not found: " + imagePath);
                    }
                } catch (Exception e) {
                    System.err.println("Error loading image: " + e.getMessage());
                }

                // Set the HBox as the cell's graphic
                setGraphic(rowBox);
            }
        }

    }
    private void displayEventData() {
        ProduitService evenmentService = new ProduitService();
        List<Produit> evenments = evenmentService.readProduit();

        // Create an ObservableList to store the event data
        ObservableList<String> eventDataList = FXCollections.observableArrayList();

        // Add column titles
        String columnTitles = String.format("%-20s %-20s %-20s %-20s %-20s", "NomProduit", "Quantite", "Prix", "NomCategorie", "Image Path");
        eventDataList.add(columnTitles);

        // Iterate through the list of events and add their details to the eventDataList
        for (Produit produit : evenments) {
            String eventData = String.format("%-20s %-20s %-20s %-20s %-20s",
                    produit.getNomProduit(),
                    produit.getQuantite(),
                    produit.getPrix(),
                    produit.getCategorie().getNomCategorie(),
                    produit.getImageProduit());
            eventDataList.add(eventData);
        }

        // Set items for the table ListView
        produitlistview.setItems(eventDataList);

        // Set custom ListCell to format the data in columns
        produitlistview.setCellFactory(listView -> new ColumnListViewCell());
    }

    @FXML
    void ModifierScene(ActionEvent event) {
        try {
            // Load the new FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierScene.fxml"));
            Parent root = loader.load();

            // Create a new stage
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Your New Scene Title");

            // Show the new stage
            stage.show();

            // If you want to close the current scene, you can do so with the following line
            // ((Node)(event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void envoyer(ActionEvent event) {

    }


}



