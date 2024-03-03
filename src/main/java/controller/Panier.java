package controller;

import entities.Categorie;

import entities.Offre;
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
import entities.panier;
import entities.Produit;
import service.panierservice;
import org.example.MainFx;
import utils.DataSource;

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
private ObservableList<Produit>po= FXCollections.observableArrayList();
    private Connection connection;
public Panier(){connection=DataSource.getInstance().getCnx();}
private ObservableList<panier>menuorder;

public ObservableList<Produit> menugetdata()
    {
        String sql="SELECT * FROM produit p JOIN categorie c ON p.categorie_id = c.idCategorie JOIN offre o ON p.idOffre = o.idOffre";
        ObservableList<Produit>lisdata=FXCollections.observableArrayList();
        try {
            PreparedStatement prepare = connection.prepareStatement(sql);
            ResultSet rs= prepare.executeQuery();
            Produit proo;
            while (rs.next()) {
                Categorie c = new Categorie(rs.getInt("c.idCategorie"), rs.getString("c.nomCategorie"),rs.getString("c.imageCategorie"));
                Offre o=new Offre(rs.getInt("o.idOffre"), rs.getString("o.descriptionOffre"), rs.getString("o.nomOffre"),rs.getDate("o.dateDebut"),rs.getDate("o.dateFin") );
                proo=new Produit(rs.getString("nomProduit"),rs.getInt("quantite"),rs.getInt("prix"), c,rs.getString("imageProduit"),o);
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
              load.setLocation(getClass().getResource("/carte.fxml"));
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
        FXMLLoader fxmlLoader = new FXMLLoader(MainFx.class.getResource("/validercommande.fxml"));
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

