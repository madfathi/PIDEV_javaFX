package tn.esprit.guiapplicatio.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import tn.esprit.guiapplicatio.models.CodePromo;
import javafx.fxml.FXMLLoader;
import java.io.IOException;

import tn.esprit.guiapplicatio.models.commande;
import tn.esprit.guiapplicatio.services.SeanceService;
import tn.esprit.guiapplicatio.test.HelloApplication;

import tn.esprit.guiapplicatio.services.PromoService;
import tn.esprit.guiapplicatio.services.UserService;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class AfficherCode {

    @FXML
    private ListView<Integer> codeCol;

    @FXML
    private ListView<Date> dateCol;

    @FXML
    private ListView<Integer> idCol;

    @FXML
    private ListView<Integer> useridCol;

    @FXML
    private ListView<Integer> utiliseCol;

    private PromoService css = new PromoService();

    public AfficherCode() throws SQLException {
    }

    @FXML
    void VersAjouter(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/tn/esprit/guiapplicatio/AjouterCode.fxml"));
        try {
           dateCol.getScene().setRoot(fxmlLoader.load());
        } catch (IOException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @FXML
    void VersModifier(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/tn/esprit/guiapplicatio/ModifierCode.fxml"));
        try {
            dateCol.getScene().setRoot(fxmlLoader.load());
        } catch (IOException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }


    @FXML
    void VersSupprimer(ActionEvent event) {

         int selected=codeCol.getSelectionModel().getSelectedIndex();
        int id= useridCol.getItems().get(selected);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Voulez-Vous Supprimer cet Categorie?");
        alert.setContentText("Supprimer?");
        ButtonType okButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);
        ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(okButton, noButton, cancelButton);
        alert.showAndWait().ifPresent(type -> {
            if (type == okButton) {
                try {
                    css.supprimer(id);
                    initialize();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } else if (type == noButton) {

                initialize();


            } else {

                initialize();


            }
        });

    }

    @FXML
    void initialize() {
        PromoService promoService = null;
        try {
            promoService = new PromoService();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            List<CodePromo> codesPromo = promoService.recupperer();
            ObservableList<Integer> codeList = FXCollections.observableArrayList();
            ObservableList<Date> dateList = FXCollections.observableArrayList();
       //     ObservableList<Integer> idList = FXCollections.observableArrayList();
            ObservableList<Integer> userIdList = FXCollections.observableArrayList();
            ObservableList<Integer> montantList = FXCollections.observableArrayList();

            for (CodePromo codePromo : codesPromo) {
                codeList.add(codePromo.getCode());
                dateList.add(codePromo.getDate_exp());
             //   idList.add(codePromo.getId());
                userIdList.add(codePromo.getUser_id());
                montantList.add(codePromo.getmontant());
            }

            codeCol.setItems(codeList);
            dateCol.setItems(dateList);
           // idCol.setItems(idList);
            useridCol.setItems(userIdList);
            utiliseCol.setItems(montantList);

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

    }

    public void RetourMenu(ActionEvent actionEvent) {
    }
}
