package tn.esprit.guiapplicatio.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import tn.esprit.guiapplicatio.models.Seance;
import tn.esprit.guiapplicatio.services.SeanceService;

import java.sql.SQLException;


import java.util.List;

public class AjouterSeance {

    @FXML
    private TableColumn<Seance,String> ca;

    @FXML
    private TextField categorieTF;

    @FXML
    private Label dashboardText;

    @FXML
    private TableColumn<Seance,Integer> du;

    @FXML
    private TextField dureeTF;

    @FXML
    private TableColumn<Seance,Integer> nb;

    @FXML
    private TextField nbTF;

    @FXML
    private TableView<Seance> tableview;

    @FXML
    private TableColumn<Seance,String> ty;

    @FXML
    private TextField typeTF;


    @FXML
    void initialize() {

        SeanceService cs2 = new SeanceService();
        try {
            List<Seance> seances= cs2.recuperer();
            ObservableList<Seance> observableList = FXCollections.observableList(seances);
            tableview.setItems(observableList);
            ty.setCellValueFactory(new PropertyValueFactory<>("type_seance"));
            ca.setCellValueFactory(new PropertyValueFactory<>("categorie"));
            du.setCellValueFactory(new PropertyValueFactory<>("duree_seance"));
            nb.setCellValueFactory(new PropertyValueFactory<>("nb_maximal") );
        } catch (SQLException e) {
            System.err.println(e.getMessage());

        }
    }
    @FXML
    public void AjouterSeance(ActionEvent actionEvent) {
        SeanceService cs = new SeanceService();
        Seance co = new Seance();
        co.setType_seance(typeTF.getText());
        co.setCategorie(categorieTF.getText());
        co.setDuree_seance(Integer.parseInt(dureeTF.getText()));
        co.setNb_maximal(Integer.parseInt(nbTF.getText()));
        try {
            cs.ajouter(co);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION) ;
            alert.setTitle("Success");
            alert.setContentText("client ajoutée");
            alert.showAndWait();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR) ;
            alert.setTitle("Erreur");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
        SeanceService cs2 = new SeanceService();
        try {
          List<Seance> seances= cs2.recuperer();
            ObservableList<Seance> observableList = FXCollections.observableList(seances);
            tableview.setItems(observableList);
            ty.setCellValueFactory(new PropertyValueFactory<>("type_seance"));
            ca.setCellValueFactory(new PropertyValueFactory<>("categorie"));
            du.setCellValueFactory(new PropertyValueFactory<>("duree_seance"));
            nb.setCellValueFactory(new PropertyValueFactory<>("nb_maximal") );
        } catch (SQLException e) {
            System.err.println(e.getMessage());

        }



        }


    }







