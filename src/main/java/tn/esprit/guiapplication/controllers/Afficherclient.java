package tn.esprit.guiapplication.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import tn.esprit.guiapplication.Cellule.ClientCell;
import tn.esprit.guiapplication.models.Client;
import tn.esprit.guiapplication.services.ClientService;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tn.esprit.guiapplication.test.HelloApplication;

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





   /* @FXML
    void initialize() {
        ClientService clientService = new ClientService();
        try {
            List<Client> clients = clientService.recuperer();
            ObservableList<Client> observableList = FXCollections.observableList(clients);
            listView.setItems(observableList);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

    }
*/

   /* @FXML
    void initialize() {
        ClientService clientService = new ClientService();
        try {
            List<Client> clients = clientService.recuperer();

            // Create an ObservableList to store the clients
            ObservableList<Client> clientList = FXCollections.observableArrayList(clients);

            // Set items for the ListView
            listView.setItems(clientList);

            // Set custom ListCell to format the data in columns
            listView.setCellFactory(clientListView -> new ListCell<Client>() {
                @Override
                protected void updateItem(Client client, boolean empty) {
                    super.updateItem(client, empty);
                    if (empty || client == null) {
                        setText(null);
                    } else {
                        // Format the client details as needed
                        String clientDetails = String.format("ID: %d, Age: %d, Poids: %d, Nom: %s, Prénom: %s",
                                client.getId_c(),
                                client.getAge(),
                                client.getPoids(),
                                client.getNom(),
                                client.getPrenom());
                        setText(clientDetails);
                    }
                }
            });

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        listView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                // Récupérer le client sélectionné
                Client selectedClient = listView.getSelectionModel().getSelectedItem();

                // Afficher les informations du client dans les champs de texte
              //  idTFm.setText(String.valueOf(selectedClient.getId_c()));
                nomTFm.setText(selectedClient.getNom());
                prenomTFm.setText(selectedClient.getPrenom());
                ageTFm.setText(String.valueOf(selectedClient.getAge()));
                poidsTFm.setText(String.valueOf(selectedClient.getPoids()));
            }
        });
    }
    */
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
               // Récupérer le client sélectionné
               Client selectedClient = listView.getSelectionModel().getSelectedItem();

               // Afficher les informations du client dans les champs de texte
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
        ClientService clientService = new ClientService(); // Create an instance of ClientService
        List<Client> clients = null; // Call the non-static method on the instance

        clients = clientService.getAll();
        clientService.sortByClient(clients);
        ObservableList<Client> observableList = FXCollections.observableArrayList();
        for (Client c : clients) {
            observableList.add(c);
        }
        listView.setItems(observableList);
        refreshListView(clients);


    }

/*

    @FXML
    void modifierClient(ActionEvent event) throws SQLException {
        String idText = idTFm.getText();
        String nomText = nomTFm.getText();
        String prenomText = prenomTFm.getText();
        String ageText = ageTFm.getText();
        String poidsText = poidsTFm.getText();

        // Vérification du champ ID
        if (idText.isEmpty()) {
            afficherMessageErreur("Veuillez saisir l'ID.");
            return;
        }
        int id_c;
        try {
            id_c = Integer.parseInt(idText);
        } catch (NumberFormatException e) {
            afficherMessageErreur("L'ID doit être un nombre entier.");
            return;
        }

        // Vérification du champ nom
        if (nomText.isEmpty()) {
            afficherMessageErreur("Veuillez saisir le nom.");
            return;
        }


        // Vérification du champ prénom
        if (prenomText.isEmpty()) {
            afficherMessageErreur("Veuillez saisir le prénom.");
            return;
        }

        // Vérification du champ âge
        if (ageText.isEmpty()) {
            afficherMessageErreur("Veuillez saisir l'âge.");
            return;
        }
        int age;
        try {
            age = Integer.parseInt(ageText);
        } catch (NumberFormatException e) {
            afficherMessageErreur("L'âge doit être un nombre entier.");
            return;
        }

        // Vérification du champ poids
        if (poidsText.isEmpty()) {
            afficherMessageErreur("Veuillez saisir le poids.");
            return;
        }
        int poids;
        try {
            poids = Integer.parseInt(poidsText);
        } catch (NumberFormatException e) {
            afficherMessageErreur("Le poids doit être un nombre entier.");
            return;
        }

        // Si toutes les vérifications sont passées, créer un nouveau client et le modifier
        Client co = new Client(id_c, age, poids, nomText, prenomText);
        ClientService clientService = new ClientService();
        clientService.modifier(co);

        // Afficher une confirmation
        Alert al = new Alert(Alert.AlertType.INFORMATION);
        al.setTitle("Succès");
        al.setHeaderText(null);
        al.setContentText("Client modifié avec succès.");
        al.showAndWait();

        // Réinitialiser les champs
        initialize();
    }
*/
    @FXML
public void modifierClient(ActionEvent event) {
    ClientService clientService = new ClientService();

    // Retrieve the selected category from the ListView
    Client selectedClient = listView.getSelectionModel().getSelectedItem();

    // Check if a category is selected
    if (selectedClient != null) {
        // Set the name from the text field to the selected category
        selectedClient.setNom(nomTFm.getText());
        selectedClient.setPrenom(prenomTFm.getText());
        selectedClient.setAge(Integer.parseInt(ageTFm.getText()));
        selectedClient.setPoids(Integer.parseInt(poidsTFm.getText()));
        selectedClient.setHauteur(Integer.parseInt(hauteurTFm.getText()));

        try {
            // Print debug information
            System.out.println("Updating client with id_c: " + selectedClient.getId_c() + ", new nom_c: " + selectedClient.getNom());

            // Call the modifier method with the selected category
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


    @FXML
    private void afficherMessageErreur(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur de saisie");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }







    @FXML
    public void ajouterProgram(ActionEvent actionEvent) {
        {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/tn/esprit/guiapplication/Ajouterprogram.fxml"));
            try {
                ageTFm.getScene().setRoot(fxmlLoader.load());
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }


        }

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

/*
    public void trouverClient(ActionEvent actionEvent) {
        int idClient = Integer.parseInt(idTFm.getText());
        ClientService clientService = new ClientService();
        Client clientTrouvee = clientService.getClientById(idClient);

        if (clientTrouvee != null) {
            System.out.println("Client trouvee : " + clientTrouvee.getId_c());
            idTFm.setText(String.valueOf(clientTrouvee.getId_c()));
            nomTFm.setText(clientTrouvee.getNom());
            prenomTFm.setText(clientTrouvee.getPrenom());
            ageTFm.setText(String.valueOf(clientTrouvee.getAge()));
            poidsTFm.setText(String.valueOf(clientTrouvee.getPoids()));
        } else {
            System.out.println("Aucune client trouvée avec l'ID : " + idClient);
        }

    }
*/
public void trouverClient(ActionEvent actionEvent) {
    String nomClient = nomTFm.getText(); // Get the name from the TextField
    ClientService clientService = new ClientService();
    Client clientTrouvee = clientService.getClientByID(nomClient); // Adjust the service method to retrieve by name

    if (clientTrouvee != null) {
        System.out.println("Client trouvé : " + clientTrouvee.getNom());
       // idTFm.setText(String.valueOf(clientTrouvee.getId_c()));
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
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/tn/esprit/guiapplication/Ajouterclient.fxml"));
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





