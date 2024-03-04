package tn.esprit.guiapplicatio.controllers;
import tn.esprit.guiapplicatio.services.ChatBotConstants;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.net.URL;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
public class ChatBotController implements Initializable {

    /**
     * Initializes the controller class.
     */
    // Couleur des messages de l'utilisateur
    private static final String USER_MESSAGE_STYLE = "-fx-background-color: #87CEEB ; -fx-padding: 5px; -fx-border-radius: 10px;";
    // Couleur des messages du chatbot
    private static final String CHATBOT_MESSAGE_STYLE = "-fx-background-color: #D3D3D3; -fx-padding: 5px; -fx-border-radius: 10px;";

    @FXML
    private Label lblTitle;
    @FXML
    private VBox messageContainer;
    @FXML
    private TextField txtInput;
    @FXML
    private Button btnSend;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Set button action
        btnSend.setOnAction(event -> {
            String input = txtInput.getText();
            String response = getResponse(input);
            appendUserMessage(input);
            appendChatbotMessage(response);
            txtInput.clear();
        });
    }

    private String getResponse(String input) {
        input = input.toLowerCase(); // Convert input to lowercase once for efficiency

        // Check for greetings and respond accordingly
        if (input.contains("salut") || input.contains("bonjour") || input.contains("bonsoir") || input.contains("aussi")) {
            return "Salut, comment puis-je vous aider avec votre entraînement ou nos produits de gym ?";
        }
        // Response for inquiries about gym equipment
        else if (input.contains("produits") || input.contains("matériel de gym")) {
            return "Nous offrons une large gamme d'équipements pour votre gym, incluant des haltères, des tapis roulants, des vélos d'appartement, et bien plus. Vous cherchez quelque chose en particulier ?";
        }
        else if (input.contains("optimum nutrition") || input.contains("dymatize") || input.contains("Vega") || input.contains("gym wieght")|| input.contains("gold")|| input.contains("recovery") ) {
            return "ces produits sont disponibles dans notre ''market place'' . avez vous un problem lors de passage d'un commande?";
        }
        else if (input.contains("Oui")) {
            return "vous pouvez contacter nons administrateurs Amrou qui est responsable de votre gestion";
        }

        // Response for questions about gym membership
        else if (input.contains("abonnement") || input.contains("membre")) {
            return "Nos abonnements de gym offrent un accès complet à toutes les installations, y compris les cours de groupe et l'espace spa. Nous avons des plans mensuels et annuels disponibles. Souhaitez-vous plus d'informations sur les prix ou les avantages ?";
        }
        // Response for inquiries about fitness programs
        else if (input.contains("programme d'entraînement") || input.contains("cours de fitness")) {
            return "Nos programmes d'entraînement sont conçus pour tous les niveaux, des débutants aux athlètes avancés. Nous proposons des cours de yoga, de pilates, de musculation, et bien d'autres. Avez-vous une préférence ou un objectif spécifique en tête ?";
        }
        // Response for inquiries about nutrition and diet
        else if (input.contains("nutrition") || input.contains("régime") || input.contains("alimentation")) {
            return "Une bonne alimentation est cruciale pour compléter votre routine d'entraînement. Nous offrons des conseils nutritionnels personnalisés pour vous aider à atteindre vos objectifs, qu'il s'agisse de perdre du poids, de gagner en muscle ou de maintenir un mode de vie sain.";
        }
        // Response for specific equipment inquiries
        else if (input.contains("Proteine") || input.contains("Boite d'energie") || input.contains("Shakes Prêts à Boire") || input.contains("capsules des proteines")) {
            return "Nous avons des differents types de proteine de poudre et des differents boites d'energies qui sont de trés bonne qualité et verifiée par des professionelles . Souhaitez-vous des recommandations basées sur vos objectifs ?";
        }
        // Response for workout advice
        else if (input.contains("conseil d'entraînement") || input.contains("commencer à m'entraîner")
                || input.contains("gestion du poids") || input.contains("supplément alimentaire") || input.contains("protéines complètes et incomplètes") || input.contains("récupération après l'entraînement")) {
            return "Commencer une nouvelle routine d'entraînement peut être intimidant, mais nous sommes là pour aider. Nos coachs professionnels peuvent vous guider à travers un plan d'entraînement personnalisé qui correspond à votre niveau actuel et à vos objectifs. Voulez-vous prendre rendez-vous pour une consultation gratuite ?";
        }
        // Generic response for all other inputs
        else {
            return "Je suis désolé, je ne suis pas sûr de comprendre votre question concernant nos produits et services de gym. Pourriez-vous reformuler votre demande, s'il vous plaît ?";
        }
    }

    private void appendUserMessage(String message) {
        // Create a new label for the user's message
        Label userLabel = new Label(message);
        // Set the style for the user's message
        userLabel.setStyle(USER_MESSAGE_STYLE);
        // Set the width of the label to prevent truncation
        userLabel.setWrapText(true);
        userLabel.setMaxWidth(messageContainer.getWidth() * 0.5);
        // Add the label to the message container with a margin
        VBox.setMargin(userLabel, new Insets(5, 50, 5, 5));
        messageContainer.getChildren().add(userLabel);
    }

    private void appendChatbotMessage(String message) {
        // Create a new label for the chatbot's message
        Label chatbotLabel = new Label(message);
        // Set the style for the chatbot's message
        chatbotLabel.setStyle(CHATBOT_MESSAGE_STYLE);
        // Set the width of the label to prevent truncation
        chatbotLabel.setWrapText(true);
        chatbotLabel.setMaxWidth(messageContainer.getWidth() * 0.7);
        // Add the label to the message container with a margin
        VBox.setMargin(chatbotLabel, new Insets(5, 5, 5, 50));
        messageContainer.getChildren().add(chatbotLabel);
    }


}
