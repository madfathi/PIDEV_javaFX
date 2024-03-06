package tn.esprit.guiapplicatio.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import tn.esprit.guiapplicatio.test.HelloApplication;

import java.io.IOException;

public class o {
    @FXML
    private TextField iddr;
    public void open_dashboard(MouseEvent mouseEvent) {
    }

    public void ge_coch(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/tn/esprit/guiapplicatio/Description.fxml"));
        iddr.getScene().setRoot(fxmlLoader.load());
    }

    public void ge_re(ActionEvent actionEvent) {
    }

    public void ge_pee(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/tn/esprit/guiapplicatio/Panier.fxml"));
        iddr.getScene().setRoot(fxmlLoader.load());
    }

    public void ge_produi(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/tn/esprit/guiapplicatio/FrontProduit.fxml"));
        iddr.getScene().setRoot(fxmlLoader.load());
    }

    public void rowClicked(MouseEvent mouseEvent) {
    }

    public void mail(ActionEvent actionEvent) {
    }

    public void open_dashboard1(MouseEvent mouseEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/tn/esprit/guiapplicatio/sea.fxml"));
        iddr.getScene().setRoot(fxmlLoader.load());
    }

    public void ge_ev(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/tn/esprit/guiapplicatio/FrontPage.fxml"));
        iddr.getScene().setRoot(fxmlLoader.load());


    }

    public void logo(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/tn/esprit/guiapplicatio/LOGIN.fxml"));
        iddr.getScene().setRoot(fxmlLoader.load());

    }
}
