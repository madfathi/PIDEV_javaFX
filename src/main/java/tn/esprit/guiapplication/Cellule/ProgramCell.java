package tn.esprit.guiapplication.Cellule;

import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import tn.esprit.guiapplication.models.Client;
import tn.esprit.guiapplication.models.Program;

public class ProgramCell extends ListCell<Program> {
    @Override
    protected void updateItem(Program program, boolean empty) {
        super.updateItem(program, empty);

        if (empty || program == null) {
            setText(null);
            setGraphic(null);
        } else {
            // Création du label pour afficher les détails de la commande
            Label label = new Label();
            label.setStyle("-fx-font-weight: bold;-fx-text-fill: black;"); // Appliquer un style au label
            label.setWrapText(true); // Permettre le retour à la ligne automatique

            // Construction de la chaîne de caractères avec les détails de la commande
            StringBuilder sb = new StringBuilder();
              sb.append("Nom: ").append(program.getClient().getNom()).append("\n")
                      .append("Titre: ").append(program.getTitre()).append("\n")
                    .append("Niveau: ").append(program.getNiveau()).append("\n")
                    .append("Description: ").append(program.getDescription()).append("\n")
                    .append("Prix: ").append(program.getPrix());

            // Définition du texte du label
            label.setText(sb.toString());

            setGraphic(label);
        }
    }
}
