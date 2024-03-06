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
//import org.example.MainFx;
import java.io.IOException;
import java.util.Objects;

public class MenuProduitCategorieController {
    @FXML
    private Button IdMenuCategorie;

    @FXML
    public void MenuCategorie(ActionEvent actionEvent)throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/tn/esprit/guiapplicatio/AfficherCategorie.fxml"));
        IdMenuCategorie .getScene().setRoot(fxmlLoader.load());
    }

    @FXML
    public void MenuProduit(ActionEvent actionEvent)throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/tn/esprit/guiapplicatio/AjouterProduit.fxml"));
        IdMenuCategorie .getScene().setRoot(fxmlLoader.load());
    }

    @FXML
    void MenuReclamation(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/tn/esprit/guiapplicatio/Reclamation.fxml"));
        IdMenuCategorie .getScene().setRoot(fxmlLoader.load());
    }


    public void open_dashboard(MouseEvent mouseEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/tn/esprit/guiapplicatio/AjoSeance.fxml"));
        try {
           IdMenuCategorie.getScene().setRoot(fxmlLoader.load());
        } catch (IOException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }


    }


    public void ge_re(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/tn/esprit/guiapplicatio/AjoSeance.fxml"));
        IdMenuCategorie .getScene().setRoot(fxmlLoader.load());

    }

    public void ge_co(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/tn/esprit/guiapplicatio/Affichecommande.fxml"));
        IdMenuCategorie .getScene().setRoot(fxmlLoader.load());
        
    }

    public void ge_pa(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/tn/esprit/guiapplicatio/Afficherpanier.fxml"));
        IdMenuCategorie .getScene().setRoot(fxmlLoader.load());
    }

    public void ge_coa(ActionEvent actionEvent) {


    }

    public void logo(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/tn/esprit/guiapplicatio/LOGIN.fxml"));
        IdMenuCategorie .getScene().setRoot(fxmlLoader.load());
    }
}
