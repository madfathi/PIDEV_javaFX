package tn.esprit.guiapplicatio.controllers;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import  tn.esprit.guiapplicatio.models.Categorie;
import tn.esprit.guiapplicatio.services.CategorieService;
import tn.esprit.guiapplicatio.test.HelloApplication;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class AfficherCategorie {
    @FXML
    private ListView<String> table;


    @FXML
    void returnTF(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/tn/esprit/guiapplicatio/AjouterCategorie.fxml"));
        Parent root = fxmlLoader.load();

table.getScene().setRoot(root);
    }

    @FXML
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

                // Add labels for each column
                for (String column : columns) {
                    Label label = new Label(column);
                    label.setPrefWidth(150); // Set the width as needed
                    rowBox.getChildren().add(label);
                }

                // Set the HBox as the cell's graphic
                setGraphic(rowBox);
            }
        }
    }



    @FXML
    void trier(ActionEvent event) {
        CategorieService categorieService = new CategorieService();
        try {
            List<Categorie> categories = categorieService.tri();
            ObservableList<String> observableList = FXCollections.observableArrayList();
            for (Categorie categorie : categories) {
                String eventData = String.format("%-20s",
                        categorie.getNomCategorie());
//                        categorie.getLieu(),
//                        categorie.getNom(),
//                        categorie.getStar(),
//                        categorie.getImagePath());
                observableList.add(eventData);
            }
            table.setItems(observableList);
            // Set custom ListCell to format the data in columns
            table.setCellFactory(listView -> new ColumnListViewCell());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    private void displayEventData() {
        CategorieService categorieService = new CategorieService();
        List<Categorie> categories = categorieService.readCategorie();

        // Create an ObservableList to store the event data
        ObservableList<String> eventDataList = FXCollections.observableArrayList();

        // Add column titles
        String columnTitles = String.format("%-20s", "Nom");
        eventDataList.add(columnTitles);

        // Iterate through the list of events and add their details to the eventDataList
        for (Categorie evenment : categories) {
            String eventData = String.format("%-20s",
                    evenment.getNomCategorie());
            eventDataList.add(eventData);
        }

        // Set items for the table ListView
        table.setItems(eventDataList);

        // Set custom ListCell to format the data in columns
        table.setCellFactory(listView -> new ColumnListViewCell());

    }

}
