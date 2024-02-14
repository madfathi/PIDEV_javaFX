package tn.esprit.applictiongui.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import tn.esprit.applictiongui.model.commande;
import tn.esprit.applictiongui.service.commandeservice;

import java.sql.SQLException;
import java.util.List;

public class Affichecommande {
    @FXML
    private TableView<commande> tab;

    @FXML
    private TableColumn<commande, String> taba;

    @FXML
    private TableColumn<commande, String> tabe;

    @FXML
    private TableColumn<commande, String> tabn;

    @FXML
    private TableColumn<commande, String> tabp;
    @FXML
    private TableColumn<commande, String> tabr;
    @FXML
    private TableColumn<commande, Integer> tabt;

    @FXML
    void initialize() {
        commandeservice cs=new commandeservice();
        try {
            List<commande> co=cs.recuperer();
            ObservableList<commande> ob= FXCollections.observableList(co);
            tab.setItems(ob);
            tabt.setCellValueFactory(new PropertyValueFactory<>("tel"));
            tabn.setCellValueFactory(new PropertyValueFactory<>("nom"));
            tabp.setCellValueFactory(new PropertyValueFactory<>("pre"));
            tabe.setCellValueFactory(new PropertyValueFactory<>("mail"));
            taba.setCellValueFactory(new PropertyValueFactory<>("addr"));
            tabr.setCellValueFactory(new PropertyValueFactory<>("pani"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
