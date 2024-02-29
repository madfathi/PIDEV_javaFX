package tn.esprit.applictiongui.controller;

import com.google.protobuf.Value;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import tn.esprit.applictiongui.model.produit;
import util.mydatabase;

import javax.swing.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Carte implements Initializable {
    private Alert alert;
    @FXML
    private Button btnadd;

    @FXML
    private AnchorPane cardform;

    @FXML
    private ImageView prod_image;

    @FXML
    private Label prod_name;

    @FXML
    private Label prod_prix;

    @FXML
    private Spinner<Integer> spinerqua;
    private SpinnerValueFactory<Integer> spin;
    private produit pro;
    private int produitt_id;
    private Connection connection;
    public Carte(){connection=mydatabase.getInstance().getConnection();}
    public void setData(produit pro){
        //System.out.println("wee");
        this.pro=pro;
        produitt_id= pro.getId();
        System.out.println(pro.getNom());
        prod_name.setText(pro.getNom());
        prod_prix.setText("DT"+String.valueOf(pro.getPrix()));
        pr=pro.getPrix();

//image=new Image(pro.getImage(),200,99,false,true);
//prod_image.setImage(image);
    }
    private int qty;

 public void setquantite(){
spin=new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100,0);
spinerqua.setValueFactory(spin);

}
private double total;
 private int pr;
public void add(ActionEvent actionEvent){
     qty=spinerqua.getValue();

     /*String check="";
    String checka="SELECT";*/
  if(qty==0 ) {
//alert.setTitle("waywa");
//alert.setContentText("rawmouh bel jben");
  }else {
      String insertData="INSERT INTO panier (quantite,nomp,img,pt,prod_id) VALUES(?,?,?,?,?)";
      try {
          PreparedStatement pre=connection.prepareStatement(insertData);
           pre.setString(1, String.valueOf(qty));
           pre.setString(2,prod_name.getText());
           pre.setString(3, "prod_image.getAccessibleHelp()");
          total=(qty*pr);
          pre.setString(4, String.valueOf(total));
          pre.setString(5, String.valueOf(produitt_id));
          pre.executeUpdate();
          alert=new Alert(Alert.AlertType.INFORMATION);
          alert.setTitle("messsage ");
          alert.setHeaderText(null);
          alert.setContentText("succes");
          alert.showAndWait();

      } catch (SQLException e) {
          throw new RuntimeException(e);
      }

  }
  }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setquantite();

    }
}
