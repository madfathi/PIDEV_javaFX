package tn.esprit.projet3a.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import tn.esprit.projet3a.models.Evenment;
import tn.esprit.projet3a.models.EventElement;
import tn.esprit.projet3a.models.Review;
import tn.esprit.projet3a.services.EvenmentService;
import tn.esprit.projet3a.services.ReviewService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class Avis {
    @FXML
    private ListView<EventElement> list;

    @FXML
    private ComboBox<Integer> combo;



    @FXML
    void initialize() throws SQLException {
        EvenmentService evenmentService = new EvenmentService();
        ReviewService reviewService = new ReviewService();

        try {
            // Retrieve the list of review IDs
            List<Review> reviews = reviewService.selectidr();
            ObservableList<Integer> reviewIds = FXCollections.observableArrayList();

            // Add each review ID to the ComboBox
            for (Review review : reviews) {
                reviewIds.add(review.getId_review());
            }

            // Set the items of the ComboBox
            combo.setItems(reviewIds);

            // Populate the ListView with event data
            List<EventElement> evenments = evenmentService.afficheravis();
            ObservableList<EventElement> observableList = FXCollections.observableList(evenments);

            // Set custom cell factory to display data in a better way
            list.setCellFactory(param -> new CustomEventCell());
            list.setItems(observableList);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }


    public class CustomEventCell extends ListCell<EventElement> {

        @Override
        protected void updateItem(EventElement item, boolean empty) {
            super.updateItem(item, empty);

            if (empty || item == null) {
                setText(null);
                setGraphic(null);
            } else {
                VBox container = new VBox(5); // VBox to hold event name and review description
                container.setAlignment(Pos.CENTER_LEFT);

                if (item instanceof Evenment) {
                    Evenment event = (Evenment) item;
                    Label eventNameLabel = new Label("Event Name     : " + event.getNom_event());
                    eventNameLabel.setFont(Font.font(14));
                    container.getChildren().add(eventNameLabel);

                } else if (item instanceof Review) {
                    Review review = (Review) item;
                    Label reviewLabel = new Label("AVIS               : " + review.getDescription());
                    reviewLabel.setFont(Font.font(12));
                    container.getChildren().add(reviewLabel);
                    Label nbrstarlabel = new Label("NOMBRES DE STARS  : " + review.getNbr_star());
                    reviewLabel.setFont(Font.font(12));
                    container.getChildren().add(nbrstarlabel);
                    Label idreviewLabel = new Label("ID Review        : " + review.getId_review());
                   idreviewLabel.setFont(Font.font(12));
                    container.getChildren().add(idreviewLabel);

                }

                setGraphic(container);
                setText(null);
            }
        }
    }



    @FXML
    void supprimerAvis(ActionEvent event) {
        // Get the selected ID from the ComboBox
        Integer selectedReviewId = combo.getValue();

        if (selectedReviewId == null) {
            // If no item is selected, show an error message and return
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Veuillez sélectionner un avis à supprimer.");
            alert.showAndWait();
            return;
        }

        // Proceed with deleting the selected review
        try {
            ReviewService reviewService = new ReviewService();

            // Call the supprimer method with the selected review ID
            reviewService.supprimer(selectedReviewId);

            // Show success message
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Avis supprimé avec succès");
            alert.showAndWait();

            // Optionally, clear the ComboBox selection after deletion
            combo.setValue(null);

        } catch (SQLException e) {
            // Show error message if an exception occurs
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }


    @FXML
    void returnA(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/AdminEvenment.fxml"));
        try {
            list.getScene().setRoot(fxmlLoader.load());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

    }


}
