package tn.esprit.guiapplicatio.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import tn.esprit.guiapplicatio.models.Categorie;
import tn.esprit.guiapplicatio.models.Offre;
import tn.esprit.guiapplicatio.models.Produit;
import tn.esprit.guiapplicatio.models.Seance;
//import org.example.MainFx;
import tn.esprit.guiapplicatio.test.HelloApplication;
import tn.esprit.guiapplicatio.utils.DataSource;
import tn.esprit.guiapplicatio.utils.MyDatabase;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class sea implements Initializable {

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
    private TableView<sea> menutab;

    @FXML
    private Label menutotal;
    private ObservableList<Seance>po= FXCollections.observableArrayList();

    private Connection connection;
    public sea(){connection=MyDatabase.getInstance().getConnection();}

    private ObservableList<sea>menuorder;
    public ObservableList<Seance> menugetdata()
    {
        String sql="SELECT * FROM seance";
        ObservableList<Seance>lisdata=FXCollections.observableArrayList();
        try {
            PreparedStatement prepare = connection.prepareStatement(sql);
            ResultSet rs= prepare.executeQuery();
            Produit proo;
            while (rs.next()) {
             //   Categorie c = new Categorie(rs.getInt("c.idCategorie"), rs.getString("c.nomCategorie"),rs.getString("c.imageCategorie"));
                Seance o=new Seance(rs.getInt("id_seance"),rs.getString("duree_seance"),rs.getInt("nb_maximal"),rs.getString("type_seance"),rs.getString("categorie"),rs.getDate("date_fin"));
               // proo=new Produit(rs.getString("nomProduit"),rs.getInt("quantite"),rs.getInt("prix"), c,rs.getString("imageProduit"),o);
                lisdata.add(o);
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
                load.setLocation(getClass().getResource("/tn/esprit/guiapplicatio/carte1.fxml"));
                AnchorPane pane=load.load();
                Carte1 car=load.getController();
                car.setData(po.get(i));

                if(colum==3) {
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
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/tn/esprit/guiapplicatio/validercommande.fxml"));
        try {
            menugrid.getScene().setRoot(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        menudisplay();

    }

    public void open_dashboard(MouseEvent mouseEvent) {
    }

    public void ge_coch(ActionEvent actionEvent) {
    }

    public void ge_re(ActionEvent actionEvent) {
    }

    public void ge_pee(ActionEvent actionEvent) {
        
    }

    public void ge_produi(ActionEvent actionEvent) {
    }
}
