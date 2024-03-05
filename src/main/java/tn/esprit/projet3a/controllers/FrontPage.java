package tn.esprit.projet3a.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import tn.esprit.projet3a.models.Evenment;
import tn.esprit.projet3a.models.Review;
import tn.esprit.projet3a.services.EvenmentService;
import tn.esprit.projet3a.services.ReviewService;
import tn.esprit.projet3a.test.HelloApplication;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
public class FrontPage {

    @FXML
    private VBox eventContainer; // Container to hold event nodes

    private EvenmentService evenmentService;
    private ReviewService reviewService;

    public FrontPage() {
        this.evenmentService = new EvenmentService();
        this.reviewService = new ReviewService();
    }


    @FXML
    public void initialize() {
        try {
            List<Evenment> events = evenmentService.recuperer(); // Fetch events from the service

            // Iterate over the events and create nodes to display them
            for (Evenment event : events) {
                // Create a label to display event name
                Label nameLabel = new Label("Nom: " + event.getNom_event());

                // Create a label to display event date
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                Label dateLabel = new Label("Date: " + dateFormat.format(event.getDate_event()));

                // Create a label to display event location
                Label lieuLabel = new Label("Lieu: " + event.getLieu_event());

                // Create a label to display star's name
                Label starLabel = new Label("Star: " + event.getNom_star());

                // Create an image view to display event image
                ImageView imageView = new ImageView();
                String imagePath = event.getImage();
                if (imagePath != null && !imagePath.isEmpty()) {
                    File file = new File(imagePath);
                    if (file.exists()) {
                        Image image = new Image(file.toURI().toString());
                        imageView.setImage(image);
                        imageView.setFitWidth(200); // Adjust width as needed
                        imageView.setPreserveRatio(true);
                    }
                }

                // Create a button for each event
                Button actionButton = new Button("Publier");
                actionButton.setOnAction(e -> {
                    // Define the action to be performed when the button is clicked
                    // For example, you can open a new window, display details, etc.
                    // Replace the following comment with your desired action
                    System.out.println("Button clicked for event: " + event.getNom_event());
                });

                // Create a text field for each event
                TextField textField = new TextField();
                textField.setPromptText("Commentaire");
                textField.setPrefColumnCount(20); // Set preferred column count

                // Create an HBox to hold the rating buttons
                HBox ratingBox = new HBox();
                ratingBox.setSpacing(15); // Set spacing between buttons

                // Create rating buttons
                List<Button> starButtons = new ArrayList<>();
                // Inside your loop for creating star buttons
                for (int i = 0; i < 5; i++) {
                    Button starButton = new Button(String.valueOf(i + 1));
                    starButton.getStyleClass().add("star-button");
                    int finalI = i; // Need to make the variable final for lambda expression
                    starButton.setOnAction(actionEvent -> {
                        for (int j = 0; j < starButtons.size(); j++) {
                            Button btn = starButtons.get(j);
                            if (j <= finalI) {
                                btn.getStyleClass().remove("unselected");
                                btn.getStyleClass().add("selected");
                            } else {
                                btn.getStyleClass().remove("selected");
                                btn.getStyleClass().add("unselected");
                            }
                        }
                    });
                    starButtons.add(starButton);
                    ratingBox.getChildren().add(starButton);
                }

                // Create an HBox to hold the button and text field
                HBox hbox = new HBox(actionButton, textField, ratingBox);
                hbox.setSpacing(10); // Set spacing between nodes

                // Add all labels, image view, and hbox to a VBox for each event
                VBox eventBox = new VBox(nameLabel, dateLabel, lieuLabel, starLabel, imageView, hbox);
                eventBox.setStyle("-fx-border-color: black; -fx-border-width: 1px; -fx-padding: 5px;");

                // Add action to the button
                actionButton.setOnAction(e -> {
                    // Check if both description and stars are filled
                    if (!textField.getText().isEmpty() && isStarsSelected(starButtons)) {
                        try {
                            // Add review to the database
                            Review review = new Review();
                            review.setId_event(event.getId_event());
                            review.setDescription(textField.getText());
                            review.setNbr_star(getSelectedStars(starButtons));
                            ReviewService reviewService = new ReviewService();
                            reviewService.ajouter(review);
                            // Clear text field
                            textField.clear();

                            // Unselect stars
                            for (Button starButton : starButtons) {
                                starButton.getStyleClass().remove("selected");
                                starButton.getStyleClass().add("unselected");
                            }
                        } catch (SQLException ex) {
                            ex.printStackTrace(); // Handle database errors
                        }
                    }
                });

                eventContainer.getChildren().add(eventBox); // Add the VBox to the container
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle database errors
        }
    }

    // Check if stars are selected
    private boolean isStarsSelected(List<Button> starButtons) {
        for (Button button : starButtons) {
            if (button.getStyleClass().contains("selected")) {
                return true;
            }
        }
        return false;
    }

    // Get the number of selected stars
    private int getSelectedStars(List<Button> starButtons) {
        int stars = 0;
        for (Button button : starButtons) {
            if (button.getStyleClass().contains("selected")) {
                stars++;
            }
        }
        return stars;
    }

    @FXML
    void acceuil1(MouseEvent event) {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/PowerFit.fxml"));
        try {
            eventContainer.getScene().setRoot(fxmlLoader.load());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @FXML
    void exit2(MouseEvent event) {
        System.exit(0);
    }

}