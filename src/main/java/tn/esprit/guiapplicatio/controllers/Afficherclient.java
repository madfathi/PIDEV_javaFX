package tn.esprit.guiapplicatio.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import tn.esprit.guiapplicatio.Cellule.ClientCell;
import tn.esprit.guiapplicatio.models.Client;
import tn.esprit.guiapplicatio.services.ClientService;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tn.esprit.guiapplicatio.test.HelloApplication;

import java.sql.SQLException;
import java.util.Optional;

import javafx.scene.control.ListView;


//import static jdk.internal.org.jline.utils.InfoCmp.Capability.tab;

public class Afficherclient {
    @FXML
    private TextField ageTFm;

    @FXML
    private TextField idTFm;

    @FXML
    private TextField nomTFm;

    @FXML
    private TextField poidsTFm;
    @FXML
    private TextField hauteurTFm;

    @FXML
    private TextField prenomTFm;

    @FXML
    private ListView<Client> listView;






    @FXML
   void initialize() {
       ClientService clientService = new ClientService();
       try {
           List<Client> clients = clientService.recuperer();
           listView.setCellFactory(param -> new ClientCell());
           ObservableList<Client> observableList = FXCollections.observableList(clients);

           listView.setItems(observableList);
       } catch (SQLException e) {
           System.err.println(e.getMessage());
       }
       listView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
           if (newValue != null) {

               Client selectedClient = listView.getSelectionModel().getSelectedItem();

               nomTFm.setText(selectedClient.getNom());
               prenomTFm.setText(selectedClient.getPrenom());
               ageTFm.setText(String.valueOf(selectedClient.getAge()));
               poidsTFm.setText(String.valueOf(selectedClient.getPoids()));
               hauteurTFm.setText(String.valueOf(selectedClient.getHauteur()));
           }
       });

   }


    private void refreshListView(List<Client> clients) {

    }

    @FXML

    void sort(ActionEvent event) {
        ClientService clientService = new ClientService();
        List<Client> clients = null;

        clients = clientService.getAll();
        clientService.sortByClient(clients);
        ObservableList<Client> observableList = FXCollections.observableArrayList();
        for (Client c : clients) {
            observableList.add(c);
        }
        listView.setItems(observableList);
        refreshListView(clients);


    }


    @FXML
public void modifierClient(ActionEvent event) {
    ClientService clientService = new ClientService();

    // Retrieve the selected category from the ListView
    Client selectedClient = listView.getSelectionModel().getSelectedItem();

    // Check if a category is selected
    if (selectedClient != null) {

        selectedClient.setNom(nomTFm.getText());
        selectedClient.setPrenom(prenomTFm.getText());
        selectedClient.setAge(Integer.parseInt(ageTFm.getText()));
        selectedClient.setPoids(Integer.parseInt(poidsTFm.getText()));
        selectedClient.setHauteur(Integer.parseInt(hauteurTFm.getText()));

        try {

            System.out.println("Updating client with id_c: " + selectedClient.getId_c() + ", new nom_c: " + selectedClient.getNom());


            clientService.modifier(selectedClient);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Succes");
            alert.setContentText("Client modifiée");
            alert.showAndWait();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }


    } else {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Avertissement");
        alert.setContentText("Veuillez sélectionner une client à modifier.");
        alert.showAndWait();
    }
        initialize();
}


    public void supprimerclient(ActionEvent actionEvent) {
        ClientService clientService = new ClientService();
        Client client = new Client();
        client.setNom(nomTFm.getText());
        try {
            clientService.supprimer(client.getNom());
            Alert alert =new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Succes");
            alert.setContentText("Client supprimée");
            alert.showAndWait();
            initialize();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText(e.getMessage());
            alert.showAndWait();

        }
        initialize();
    }


public void trouverClient(ActionEvent actionEvent) {
    String nomClient = nomTFm.getText();
    ClientService clientService = new ClientService();
    Client clientTrouvee = clientService.getClientByID(nomClient); // Adjust the service method to retrieve by name

    if (clientTrouvee != null) {
        System.out.println("Client trouvé : " + clientTrouvee.getNom());
        nomTFm.setText(clientTrouvee.getNom());
        prenomTFm.setText(clientTrouvee.getPrenom());
        ageTFm.setText(String.valueOf(clientTrouvee.getAge()));
        poidsTFm.setText(String.valueOf(clientTrouvee.getPoids()));
        hauteurTFm.setText(String.valueOf(clientTrouvee.getHauteur()));
    } else {
        System.out.println("Aucun client trouvé avec le nom : " + nomClient);
    }
}

    public void retour(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/tn/esprit/guiapplicatio/Ajouterclient.fxml"));
        try {
            nomTFm.getScene().setRoot(fxmlLoader.load());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void clearFields(ActionEvent actionEvent) {
        nomTFm.clear();
        prenomTFm.clear();
        ageTFm.clear();
        poidsTFm.clear();
        hauteurTFm.clear();
    }
}





