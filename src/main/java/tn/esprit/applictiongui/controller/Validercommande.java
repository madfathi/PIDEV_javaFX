package tn.esprit.applictiongui.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import tn.esprit.applictiongui.model.commande;
import tn.esprit.applictiongui.model.panier;
import tn.esprit.applictiongui.model.produit;
import tn.esprit.applictiongui.service.commandeservice;
import tn.esprit.applictiongui.service.panierservice;
import util.mydatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Validercommande {


    @FXML
    private TextField addr;

    @FXML
    private TextField mail;

    @FXML
    private TextField nom;

    @FXML
    private TextField pre;

    @FXML
    private TableView<panier> taborder;

    @FXML
    private TableColumn<?, ?> tabp;

    @FXML
    private TableColumn<?, ?> tabpro;

    @FXML
    private TableColumn<?, ?> tabq;

    @FXML
    private TextField tel;

    @FXML
    private Label tot;
    private int tota;
    private panier pro;
    private Connection connection;
    

    public Validercommande(){connection= mydatabase.getInstance().getConnection();}


    @FXML
    void ajouter(ActionEvent event) throws SQLException {
        panierservice userService = null;
        userService = new panierservice();

            List<panier> users = userService.recuperer();
        ObservableList<String> nomm = FXCollections.observableArrayList();
        ObservableList<String> we = FXCollections.observableArrayList();
        for (panier user : users) {
            nomm.add(String.valueOf(user.getPt()));
            we.add(user.getNomp());
        }
        /*String paniValue = ""; // Initialize with an empty string
        int prixValue = 0;
            String req = "SELECT nomp, pt FROM panier";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(req);
            if (rs.next()) {
                paniValue = rs.getString("nomp");
                prixValue = rs.getInt("pt");
            }*/

            // Exit the method if no records found in the "panier" table

        // Input validation
        if (nom.getText().isEmpty() || pre.getText().isEmpty() || mail.getText().isEmpty() || addr.getText().isEmpty() || tel.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText("Veuillez remplir tous les champs.");
            alert.showAndWait();
            return; // Exit the method early if any field is empty
        }

        // Validate email format
        if (!mail.getText().contains("@")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText("Veuillez entrer une adresse e-mail valide.");
            alert.showAndWait();
            return; // Exit the method early if email format is invalid
        }

        // Validate phone number format
        try {
            Integer.parseInt(tel.getText());
        } catch (NumberFormatException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText("Le numéro de téléphone doit être un nombre entier.");
            alert.showAndWait();
            return; // Exit the method early if phone number format is invalid
        }

        commandeservice cs = new commandeservice();
        commande co = new commande();
        co.setNom(nom.getText());
        co.setPre(pre.getText());
        co.setMail(mail.getText());
        co.setAddr(addr.getText());
        co.setPani(Collections.singletonList(nomm + "" + we));

        co.setTel(Integer.parseInt(tel.getText()));

        try {
            cs.ajouter(co);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Success");
            alert.setContentText("Personne ajoutée avec succès");
            alert.showAndWait();
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText("Une erreur s'est produite lors de l'ajout de la personne: " + ex.getMessage());
            alert.showAndWait();
        }
    }

public void menutotal() throws SQLException {
        String req="SELECT SUM(pt) FROM panier";
    PreparedStatement pre=connection.prepareStatement(req);
    ResultSet res=pre.executeQuery();
    if(res.next()){
        tota=res.getInt("SUM(pt)");

    }
    tot.setText(tota+""+"DT");

}
    @FXML
    void initialize() {
        panierservice pa=new panierservice();
        try {
            List<panier> co=pa.recuperer();
            ObservableList<panier> ob= FXCollections.observableList(co);
            taborder.setItems(ob);

            tabq.setCellValueFactory(new PropertyValueFactory<>("quantite"));
            tabpro.setCellValueFactory(new PropertyValueFactory<>("nomp"));
            tabp.setCellValueFactory(new PropertyValueFactory<>("pt"));

            menutotal();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}