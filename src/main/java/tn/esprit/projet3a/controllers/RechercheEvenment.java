package tn.esprit.projet3a.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import tn.esprit.projet3a.models.EventElement;
import tn.esprit.projet3a.services.EvenmentService;
import tn.esprit.projet3a.test.HelloApplication;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class RechercheEvenment {

    @FXML
    private TextField rechercheCol;
    @FXML
    private ListView<EventElement> list;

    public RechercheEvenment() {
        EvenmentService evenmentService = new EvenmentService();
    }

    @FXML
    public void initialize() {
        // Add a listener to the text property of rechercheCol TextField
        rechercheCol.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                // Call rechercheEvenment method whenever the text changes
                rechercheEvenment(newValue);
            }
        });
    }

    public void rechercheEvenment(String searchText) {
        try {
            // Parse the searchText to an integer (assuming it represents the event ID)
            int id_event = Integer.parseInt(searchText);

            // Perform the search using the EvenmentService
            EvenmentService evenmentService = new EvenmentService();
            List<EventElement> evenments = evenmentService.recherche(id_event);

            // Update the ListView with the search results
            ObservableList<EventElement> observableList = FXCollections.observableList(evenments);
            list.setItems(observableList);

        } catch (NumberFormatException | SQLException e) {
            // Handle NumberFormatException (if searchText is not a valid integer) and SQLException
            // Clear the ListView if an exception occurs
            list.getItems().clear();
            System.err.println(e.getMessage());
        }
    }

    @FXML
    void returnR(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/AdminEvenment.fxml"));
        try {
            list.getScene().setRoot(fxmlLoader.load());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

    }

}

