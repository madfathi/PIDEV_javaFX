package tn.esprit.guiapplicatio.Cellule;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import tn.esprit.guiapplicatio.models.Client;

public class ClientCell extends ListCell<Client> {




        @Override
        protected void updateItem(Client client, boolean empty) {
            super.updateItem(client, empty);

            if (empty || client == null) {
                setText(null);
                setGraphic(null);
            } else {

                Label label = new Label();
                label.setStyle("-fx-font-weight: bold;-fx-text-fill: black;");
                label.setWrapText(true);


                StringBuilder sb = new StringBuilder();

                        sb.append("Nom: ").append(client.getNom()).append("\n")
                        .append("Prénom: ").append(client.getPrenom()).append("\n")
                        .append("Age: ").append(client.getAge()).append("\n")
                        .append("Poids: ").append(client.getPoids()).append("\n")
                        .append("Hauteur: ").append(client.getHauteur());



                label.setText(sb.toString());

                setGraphic(label);
            }
        }
    }




