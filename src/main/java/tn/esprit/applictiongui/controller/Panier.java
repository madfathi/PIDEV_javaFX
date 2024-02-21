package tn.esprit.applictiongui.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import tn.esprit.applictiongui.model.panier;
import tn.esprit.applictiongui.model.produit;
import tn.esprit.applictiongui.service.panierservice;
import tn.esprit.applictiongui.test.HelloApplication;
import util.mydatabase;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.List;
import java.util.ResourceBundle;

public class Panier implements Initializable {
    @FXML
    private Button btncon;

    @FXML
    private AnchorPane menuform;

    @FXML
    private GridPane menugrid;

    @FXML
    private TableColumn<?, ?> menun;

    @FXML
    private TableColumn<?, ?> menup;

    @FXML
    private TableColumn<?, ?> menuq;

    @FXML
    private ScrollPane menuscroll;

    @FXML
    private TableView<panier> menutab;

    @FXML
    private Label menutotal;
private ObservableList<produit>po= FXCollections.observableArrayList();
    private Connection connection;
public Panier(){connection=mydatabase.getInstance().getConnection();}
private ObservableList<panier>menuorder;

public ObservableList<produit> menugetdata()
    {
        String sql="SELECT * FROM produits";
        ObservableList<produit>lisdata=FXCollections.observableArrayList();
        try {
            PreparedStatement prepare = connection.prepareStatement(sql);
            ResultSet rs= prepare.executeQuery();
            produit proo;
            while (rs.next()) {
                proo=new produit(rs.getInt("quantite"),rs.getInt("prix"),rs.getInt("id"),
                        rs.getString("categorie_produit"),rs.getString("image"),rs.getString("nom"));
                lisdata.add(proo);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lisdata;
    }
    public void menudisplay(){
        po.clear();
        po.addAll(menugetdata());
        int row=0;
        int colum=0;
        menugrid.getRowConstraints().clear();
        menugrid.getColumnConstraints().clear();
        for(int i=0;i<po.size();i++) {

            try {
                FXMLLoader load= new FXMLLoader();
              load.setLocation(getClass().getResource("/tn/esprit/applictiongui/carte.fxml"));
                AnchorPane pane=load.load();
                Carte car=load.getController();
                 car.setData(po.get(i));

                if(colum==4) {
                     colum=0;
                      row+=1;


}
menugrid.add(pane,colum++,row);



            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }


    }

    @FXML
    void coco(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/tn/esprit/applictiongui/validercommande.fxml"));
        try {
            menugrid.getScene().setRoot(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        menudisplay();

        /*panierservice ps=new panierservice();

        try {
            List<commande> co= null;
            co = ps.recuperer();
            ObservableList<commande> ob= FXCollections.observableList(co);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*/

    }
}

