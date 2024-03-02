package tn.esprit.guiapplicatio.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import tn.esprit.guiapplicatio.models.User;
import tn.esprit.guiapplicatio.services.SeanceService;
import tn.esprit.guiapplicatio.services.UserService;
import tn.esprit.guiapplicatio.test.HelloApplication;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class AfficherUsers {


    @FXML
    private ListView<String> adresseCol;

    @FXML
    private ListView<String> emailCol;

    @FXML
    private ListView<String> mdpCol;

    @FXML
    private ListView<String> nomCol;

    @FXML
    private ListView<String> prenomCol;

    @FXML
    private ListView<String> idCol;
    private UserService css = new UserService();

    public AfficherUsers() throws SQLException {
    }

    @FXML
    void RetourMenu(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/tn/esprit/guiapplicatio/AjoSeance.fxml"));
        try {
            nomCol.getScene().setRoot(fxmlLoader.load());
        } catch (IOException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }


    @FXML
    void PageSupprimer(ActionEvent event) {
      /*  FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/tn/esprit/guiapplicatio/SupprimerUser.fxml"));
        try {
            prenomCol.getScene().setRoot(fxmlLoader.load());
        } catch (IOException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }*/
        int selected=nomCol.getSelectionModel().getSelectedIndex();
        String catego = prenomCol.getItems().get(selected);
        String Dispo =mdpCol.getItems().get(selected);
        String Dispo1 =emailCol.getItems().get(selected);
        String Dispo2 =adresseCol.getItems().get(selected);

        String id= idCol.getItems().get(selected);
        System.out.println(id);

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
                    css.supprimer(Integer.parseInt(id));
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
    void PageModifier(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/tn/esprit/guiapplicatio/ModifierUser.fxml"));
        try {
            prenomCol.getScene().setRoot(fxmlLoader.load());
        } catch (IOException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @FXML
    void ReturnToAjouter(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/tn/esprit/guiapplicatio/AjouterUser.fxml"));
        try {
            prenomCol.getScene().setRoot(fxmlLoader.load());
        } catch (IOException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }

    }

    @FXML
    void initialize() {
        UserService userService = null;
        try {
            userService = new UserService();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            List<User> users = userService.recupperer();
            ObservableList<String> idList = FXCollections.observableArrayList();
            ObservableList<String> emailList = FXCollections.observableArrayList();
            ObservableList<String> nomList = FXCollections.observableArrayList();
            ObservableList<String> prenomList = FXCollections.observableArrayList();
            ObservableList<String> adresseList = FXCollections.observableArrayList();
            ObservableList<String> mdpList = FXCollections.observableArrayList(); // Nouvelle liste pour le champ mdpcol

            for (User user : users) {
                String id = String.valueOf(user.getId());
                //id = id.substring(0, 1) + id.substring(1).replaceAll("\\d", "*"); // Remplacer les chiffres après le premier par des étoiles
                idList.add(id);
                emailList.add(user.getEmail());
                nomList.add(user.getNom());
                prenomList.add(user.getPrenom());
                adresseList.add(user.getAdresse());

                // Convertir le mot de passe en étoiles
                String mdpEtoile = user.getMdp().replaceAll(".", "*");
                mdpList.add(mdpEtoile);
            }
            idCol.setItems(idList);
            emailCol.setItems(emailList);
            nomCol.setItems(nomList);
            prenomCol.setItems(prenomList);
            adresseCol.setItems(adresseList);
            mdpCol.setItems(mdpList); // Définir la liste modifiée comme la source de données pour mdpCol

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }


    @FXML
    public void trierParNom(ActionEvent event) {/*
        try {
            List<User> users = UserService.recupperer();
            Collections.sort(users, Comparator.comparing(User::getNom)); // Tri par nom

            ObservableList<String> idList = FXCollections.observableArrayList();
            ObservableList<String> emailList = FXCollections.observableArrayList();
            ObservableList<String> nomList = FXCollections.observableArrayList();
            ObservableList<String> prenomList = FXCollections.observableArrayList();
            ObservableList<String> adresseList = FXCollections.observableArrayList();
            ObservableList<String> mdpList = FXCollections.observableArrayList(); // Nouvelle liste pour le champ mdpcol

            for (User user : users) {
                idList.add(String.valueOf(user.getId()));
                emailList.add(user.getEmail());
                nomList.add(user.getNom());
                prenomList.add(user.getPrenom());
                adresseList.add(user.getAdresse());

                // Convertir le mot de passe en étoiles
                String mdpEtoile = user.getMdp().replaceAll(".", "*");
                mdpList.add(mdpEtoile);
            }
            idCol.setItems(idList);
            emailCol.setItems(emailList);
            nomCol.setItems(nomList);
            prenomCol.setItems(prenomList);
            adresseCol.setItems(adresseList);
            mdpCol.setItems(mdpList); // Définir la liste modifiée comme la source de données pour mdpCol

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }*/



    }

    public void ge_re(ActionEvent actionEvent) {



    }

    public void open_dashboard(MouseEvent mouseEvent) {
    }

    public void code_pro(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/tn/esprit/guiapplicatio/AfficherCode.fxml"));
        try {
            prenomCol.getScene().setRoot(fxmlLoader.load());
        } catch (IOException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }


    }
}


