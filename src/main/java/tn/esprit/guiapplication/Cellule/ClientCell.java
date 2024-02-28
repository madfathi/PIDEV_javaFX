package tn.esprit.guiapplication.Cellule;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import tn.esprit.guiapplication.models.Client;

public class ClientCell extends ListCell<Client> {




        @Override
        protected void updateItem(Client client, boolean empty) {
            super.updateItem(client, empty);

            if (empty || client == null) {
                setText(null);
                setGraphic(null);
            } else {
                // Création du label pour afficher les détails de la commande
                Label label = new Label();
                label.setStyle("-fx-font-weight: bold;-fx-text-fill: black;"); // Appliquer un style au label
                label.setWrapText(true); // Permettre le retour à la ligne automatique

                // Construction de la chaîne de caractères avec les détails de la commande
                StringBuilder sb = new StringBuilder();
              //  sb.append("ID: ").append(client.getId_c()).append("\n")
                        sb.append("Nom: ").append(client.getNom()).append("\n")
                        .append("Prénom: ").append(client.getPrenom()).append("\n")
                        .append("Age: ").append(client.getAge()).append("\n")
                        .append("Poids: ").append(client.getPoids()).append("\n")
                        .append("Hauteur: ").append(client.getHauteur());


                // Définition du texte du label
                label.setText(sb.toString());

                setGraphic(label);
            }
        }
    }




