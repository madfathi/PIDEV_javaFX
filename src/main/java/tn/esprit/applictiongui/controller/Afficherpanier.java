package tn.esprit.applictiongui.controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import tn.esprit.applictiongui.model.commande;
import tn.esprit.applictiongui.model.panier;
import tn.esprit.applictiongui.service.commandeservice;
import tn.esprit.applictiongui.service.panierservice;
import tn.esprit.applictiongui.test.HelloApplication;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class Afficherpanier {
    ObservableList<panier> list = FXCollections.observableArrayList();
    private final panierservice cs =new panierservice();
    private panierservice css =new panierservice();
    @FXML
    private TextField prix;

    @FXML
    private TextField qua;

    @FXML
    private TableView<panier> tab;

    @FXML
    private TableColumn<?, ?> tabi;

    @FXML
    private TableColumn<?, ?> tabip;

    @FXML
    private TableColumn<?, ?> tabn;

    @FXML
    private TableColumn<?, ?> tabp;

    @FXML
    private TableColumn<?, ?> tabq;
    public int idp;
public String nomp;
public String img;
    public String getImg(){
        return img;
    }
    public void setImg(String img) {
        this.img = img;
    }
    public String getNomp(){
        return nomp;
    }
    public int getIdp(){
        return idp;
    }
    public void setNomp(String nomp) {
        this.nomp = nomp;
    }
    public void setIdp(int id) {
        this.idp = id;
    }
    public void SetValue(MouseEvent mouseEvent) throws SQLException, ClassNotFoundException {
        panier selected = tab.getSelectionModel().getSelectedItem();

        if (selected != null) {

            qua.setText(String.valueOf(selected.getQuantite()));
            prix.setText(String.valueOf(selected.getPt()));
            idp = selected.getIdp();
            nomp = selected.getNomp();
            img = selected.getImg();


        }
    }
    @FXML
    void modifierpanier(ActionEvent event) {
       int qantite = Integer.parseInt(qua.getText());
        int prixx = Integer.parseInt(prix.getText());
        panier p = new panier(idp,qantite,nomp,img,prixx,"");
        try {
            css.modifier(p);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Alert a = new Alert(Alert.AlertType.WARNING);

        a.setTitle("Succes");
        a.setContentText("Cateygorie Modifiée");
        a.showAndWait();
        initialize();
    }
    @FXML
    void back(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/tn/esprit/applictiongui/back.fxml"));
        try {
            qua.getScene().setRoot(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    @FXML
    void supppanier(ActionEvent event) {
        panier selected=tab.getSelectionModel().getSelectedItem();
        if(selected!=null)
        {
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
                        css.supprimer(selected.getIdp());
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
    }

    @FXML
    void triasc(ActionEvent event) {
        List<panier> temp;
        temp = null;
        try {
            temp = css.tri_par_nom();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ObservableList<panier> updatedList = FXCollections.observableArrayList(temp);

        // Mettre à jour la TableView
        tab.setItems(updatedList);
    }

    @FXML
    void tridesc(ActionEvent event) {
        List<panier> temp;
        temp = null;
        try {
            temp = css.tri_par_nom2();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ObservableList<panier> updatedList = FXCollections.observableArrayList(temp);

        // Mettre à jour la TsableView
        tab.setItems(updatedList);
    }

    @FXML
    void initialize() {
        panierservice pa=new panierservice();
        try {
            List<panier> co=pa.recuperer();
            ObservableList<panier> ob= FXCollections.observableList(co);
            tab.setItems(ob);
            tabi.setCellValueFactory(new PropertyValueFactory<>("idp"));
            tabq.setCellValueFactory(new PropertyValueFactory<>("quantite"));
            tabn.setCellValueFactory(new PropertyValueFactory<>("nomp"));
            tabip.setCellValueFactory(new PropertyValueFactory<>("img"));
            tabp.setCellValueFactory(new PropertyValueFactory<>("pt"));


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
