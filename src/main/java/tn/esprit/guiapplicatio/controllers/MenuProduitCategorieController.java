package tn.esprit.guiapplicatio.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import tn.esprit.guiapplicatio.test.HelloApplication;
import java.io.IOException;
import java.util.Objects;

public class MenuProduitCategorieController {

    @FXML
    private Button IdMenuProduit;
    @FXML
    public void MenuCategorie(ActionEvent actionEvent)throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/tn/esprit/guiapplicatio/AfficherCategorie.fxml"));
        Parent root = fxmlLoader.load();

        IdMenuProduit.getScene().setRoot(root);

    }

    @FXML
    public void MenuProduit(ActionEvent actionEvent)throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/tn/esprit/guiapplicatio/AjouterProduit.fxml"));
        Parent root = fxmlLoader.load();

        IdMenuProduit.getScene().setRoot(root);

    }

    @FXML
    void MenuReclamation(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/tn/esprit/guiapplicatio/Reclamation.fxml"));
        Parent root = fxmlLoader.load();

        IdMenuProduit.getScene().setRoot(root);

    }


    public void open_dashboard(MouseEvent mouseEvent) {
    }

    public void ge_re(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/tn/esprit/guiapplicatio/AjoSeance.fxml"));
        Parent root = fxmlLoader.load();

        IdMenuProduit.getScene().setRoot(root);
    }

    public void ge_co(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/tn/esprit/guiapplicatio/Affichecommande.fxml"));
        Parent root = fxmlLoader.load();

        IdMenuProduit.getScene().setRoot(root);
    }

    public void ge_pa(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/tn/esprit/guiapplicatio/afficherpanier.fxml"));
        Parent root = fxmlLoader.load();

        IdMenuProduit.getScene().setRoot(root);
    }

    public void ge_coa(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/tn/esprit/guiapplicatio/AfficherUsers.fxml"));
        Parent root = fxmlLoader.load();

        IdMenuProduit.getScene().setRoot(root);
    }
}
