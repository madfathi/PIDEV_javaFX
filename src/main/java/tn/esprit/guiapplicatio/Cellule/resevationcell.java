package tn.esprit.guiapplicatio.Cellule;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import tn.esprit.guiapplicatio.models.Reservation;

public class resevationcell extends ListCell<Reservation>{

    @Override
    protected void updateItem(Reservation reservation, boolean empty) {
        super.updateItem(reservation, empty);

        if (empty || reservation == null) {
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
            sb.append("Type: ").append(reservation.getType_reservation()).append("\n")
                    .append("Username: ").append(reservation.getUsername()).append("\n")
                    .append("Email").append(reservation.getEmail()).append("\n")
                    .append("phone: ").append(reservation.getPhone());

            // Définition du texte du label
            label.setText(sb.toString());

            setGraphic(label);
        }
    }




}
