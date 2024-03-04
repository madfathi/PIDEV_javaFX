package tn.esprit.guiapplicatio.controllers;

import tn.esprit.guiapplicatio.models.Produit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import tn.esprit.guiapplicatio.services.ProduitService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ListViewTest {

    @FXML
    private ListView<Integer> idCol;

    @FXML
    private ListView<String> imageCol;

    @FXML
    private ListView<String> nomCol;

    @FXML
    private ListView<Integer> prixCol;

    @FXML
    private ListView<Integer> quantiteCol;

    @FXML
    private TextField idp;
    @FXML
    private TextField imagep;
    @FXML
    private TextField np;

    @FXML
    private TextField pp;
    @FXML
    private TextField qp;
    @FXML
    private TextField supprimertf;
    @FXML
    private ComboBox<String> sortProduitBox;
    @FXML
    private Button modifybutton;
    @FXML
    private Button supprimer;

    private final ProduitService pr = new ProduitService();
    private List<Produit> temp;


    @FXML
    void AfficherProduit(ActionEvent event) {

    }

    @FXML
    void initialize() throws SQLException {
        ProduitService produitService = null;
        produitService = new ProduitService();
        List<Produit> produits = produitService.readProduit();
        ObservableList<Integer> idList = FXCollections.observableArrayList();
        ObservableList<String> nomProduitList = FXCollections.observableArrayList();
        ObservableList<Integer> quantiteList = FXCollections.observableArrayList();
        ObservableList<Integer> prixList = FXCollections.observableArrayList();
        ObservableList<String> ImageProduitList = FXCollections.observableArrayList();
        // ObservableList<String> OffresList = FXCollections.observableArrayList();

        for (Produit produit : produits) {
            idList.add(produit.getIdProduit());
            nomProduitList.add(produit.getNomProduit());
            quantiteList.add(produit.getQuantite());
            prixList.add(produit.getPrix());
            ImageProduitList.add(produit.getImageProduit());
            //OffresList.add(user.getOffre());
        }
        idCol.setItems(idList);
        nomCol.setItems(nomProduitList);
        quantiteCol.setItems(quantiteList);
        prixCol.setItems(prixList);
        imageCol.setItems(ImageProduitList);
        // offrecol.setItems(OffresList);


    }

    @FXML
    void modifier(ActionEvent event) throws SQLException {
        ObservableList<Produit> updatedList;
        updatedList = FXCollections.observableArrayList();

// Récupération des données depuis SeanceService
        List<Produit> produitList = pr.readProduit();

// Ajout des données à la liste observable
        updatedList.addAll(produitList);
        int currentAnimalId = Integer.parseInt(idp.getText());
        String nomP = np.getText();
        String quantite = qp.getText();
        String prix = pp.getText();
        String imageP = imagep.getText();

        for (Produit animal : updatedList) {
            if (animal.getIdProduit() == currentAnimalId) {
                animal.setNomProduit(np.getText());
                int quantitep = Integer.parseInt(qp.getText());
                animal.setQuantite(quantitep);
                int prixp = Integer.parseInt(pp.getText());
                animal.setPrix(prixp);
                animal.setIdProduit(currentAnimalId);
                animal.setImageProduit(imagep.getText());
                ProduitService service = new ProduitService();
                service.modifyProduit(animal);


            }

        }
    }


        public void row (MouseEvent mouseEvent){
            int selectedIndex = idCol.getSelectionModel().getSelectedIndex();
            Integer ids = idCol.getItems().get(selectedIndex);
            String nom = nomCol.getItems().get(selectedIndex);
            Integer quantite = quantiteCol.getItems().get(selectedIndex);
            Integer prix = prixCol.getItems().get(selectedIndex);
            String image = imageCol.getItems().get(selectedIndex);


            np.setText(nom);
            qp.setText(String.valueOf(quantite));
            pp.setText(String.valueOf(prix));
            imagep.setText(image);
            idp.setText(String.valueOf(ids));


        }

//    @FXML
//    private void returnToAjoutProduit(ActionEvent event) throws IOException {
//        Parent ajoutProduitParent = FXMLLoader.load(getClass().getResource("/AjouterProduit.fxml"));
//        Scene ajoutProduitScene = new Scene(ajoutProduitParent);
//
//        //This line gets the stage information
//        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
//
//        window.setScene(ajoutProduitScene);
//        window.show();
//    }

    @FXML
    private void backProduit(ActionEvent event) throws IOException {
        Parent ajoutProduitParent = FXMLLoader.load(getClass().getResource("/AjouterProduit.fxml"));
        Scene ajoutProduitScene = new Scene(ajoutProduitParent);

        //This line gets the stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(ajoutProduitScene);
        window.show();
    }
    @FXML
    void supprimerProduit(ActionEvent event) {
        // Récupérer l'ID de l'utilisateur à partir du TextField
        String idProduit = supprimertf.getText();

        // Vérifier si l'ID est un entier valide
        try {
            int idproduit = Integer.parseInt(idProduit);

            // Créer une instance de ProduitService
            ProduitService produitService = new ProduitService();

            // Appeler la méthode de service pour supprimer l'utilisateur par son ID
            produitService.deleteProduit(idproduit);
            System.out.println("L'utilisateur avec l'ID " + idproduit + " a été supprimé avec succès.");
            // Vous pouvez ajouter ici des actions supplémentaires après la suppression de l'utilisateur
        } catch (NumberFormatException e) {
            System.err.println("L'ID de l'utilisateur doit être un entier valide.");
            // Gérer l'erreur si l'utilisateur entre un ID non valide (par exemple, afficher un message d'erreur à l'utilisateur)
        }

        // Afficher un message de confirmation à l'utilisateur
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Suppression");
        alert.setContentText("L'utilisateur a été supprimé avec succès.");
        alert.showAndWait();
    }
}
