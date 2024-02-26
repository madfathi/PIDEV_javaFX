package tn.esprit.guiapplicatio.controllers;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TableCell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import tn.esprit.guiapplicatio.models.Seance;
import tn.esprit.guiapplicatio.services.SeanceService;
import javafx.util.Callback;
import javafx.scene.image.Image;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Pair;
import tn.esprit.guiapplicatio.test.HelloApplication;

public class ModifierSeance {
    private TableView<Seance> tableView;
    private ObservableList<Seance> seances;

    public void setSeances(ObservableList<Seance> seances) {
        this.seances = seances;
    }


    @FXML
    private TextField id;

    @FXML
    private TextField ty;

    @FXML
    private TextField ca;

    @FXML
    private TextField du;

    @FXML
    private TextField nb;

    public void setId(String ide) {
        id.setText(ide);
    }

    public void setType(String type) {
        ty.setText(type);
    }

    public void setCategorie(String categorie) {
        ca.setText(categorie);
    }

    public void setDuree(String duree) {
        du.setText(duree);
    }

    public void setNbMaximal(String nbMaximal) {
        nb.setText(nbMaximal);

    }
    public void setdate(String nbMaximal) {
        nb.setText(nbMaximal);

    }

    public void modifier(ActionEvent actionEvent) throws IOException, SQLException {


        ObservableList<Seance> currentTableData = seances;
        int currentAnimalId = Integer.parseInt(id.getText());
        System.out.println(currentAnimalId);
        Optional<Seance> seanceOptional = seances.stream()
                .filter(seance -> seance.getId_seance() == currentAnimalId)
                .findFirst();

        if (seanceOptional.isPresent()) {
            Seance seance = seanceOptional.get();

        }

       for (Seance animal : seances) {
            if(animal.getId_seance() == currentAnimalId) {
                animal.setType_seance(ty.getText());

                animal.setCategorie(ca.getText());
                System.out.println(ca.getText());
                //int duree = Integer.parseInt(du.getText());
                int nbMaximal = Integer.parseInt(nb.getText());
                System.out.println(nb.getText());

                animal.setDuree_seance(du.getText());

                animal.setNb_maximal(nbMaximal);
              // animal.setDate_fin(null);
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/tn/esprit/guiapplicatio/AjoSeance.fxml"));


                Parent root = fxmlLoader.load();
                AjoSeance controller = fxmlLoader.getController();

              /*  if (duree < 2 || duree > 4 ) {
                    // Afficher une alerte
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur de saisie");
                    alert.setHeaderText(null);
                    alert.setContentText("duree doit contenir entre 2 et 4 chiffres.");
                    alert.showAndWait();
                    return; // Sortir de la méthode si la validation échoue
                }*/


                if (nbMaximal < 1 || nbMaximal > 10 ) {
                    // Afficher une alerte
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur de saisie");
                    alert.setHeaderText(null);
                    alert.setContentText("nbmaximale doit etre entre 1 et 10 chiffres.");
                    alert.showAndWait();
                    return; // Sortir de la méthode si la validation échoue
                }

                SeanceService service = new SeanceService();
                service.modifier(animal);
                System.out.println(animal.getId_seance());
                System.out.println(animal.getNb_maximal());

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Success");
                alert.setContentText("Seance Modifiee");
                alert.showAndWait();
controller.addDataToChart();

controller.updateTableView();

              //  controller.updateTableView(currentAnimalId);
             //   controller.updateListView(currentAnimalId,animal);
                ty.getScene().setRoot(root);

                // Replace the current scene with the updated ajoseance.fxml

            }
        }


    }
}
