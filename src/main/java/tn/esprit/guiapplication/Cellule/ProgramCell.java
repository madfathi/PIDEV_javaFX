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

            Label label = new Label();
            label.setStyle("-fx-font-weight: bold;-fx-text-fill: black;");
            label.setWrapText(true);


            StringBuilder sb = new StringBuilder();
              sb.append("Nom: ").append(program.getClient().getNom()).append("\n")
                      .append("Titre: ").append(program.getTitre()).append("\n")
                    .append("Niveau: ").append(program.getNiveau()).append("\n")
                    .append("Description: ").append(program.getDescription()).append("\n")
                    .append("Prix: ").append(program.getPrix()).append("\n")
                    .append("Image: ").append(program.getImage()).append ("\n");


            label.setText(sb.toString());

            setGraphic(label);
        }
    }
}
