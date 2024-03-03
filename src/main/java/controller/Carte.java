package controller;

import com.google.protobuf.Value;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import entities.Produit;
import utils.DataSource;

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
    private Produit pro;
    private int produitt_id;
    private Connection connection;
    public Carte(){connection=DataSource.getInstance().getCnx();}
    public void setData(Produit pro){
        this.pro = pro;
        produitt_id = pro.getIdProduit();
        System.out.println(pro.getNomProduit());
        prod_name.setText(pro.getNomProduit());
        prod_prix.setText("DT" + pro.getPrix());
        pr = pro.getPrix();

        // Assuming getImageProduit() returns just the filename, e.g., "image.jpg"
        // Construct the full path
        String baseDir = "C:/Users/LEGION/Desktop/ProduitCategorie/src/main/resources/Photos/";
        String fileName = pro.getImageProduit(); // Ensure this returns the filename like "image.jpg"
        String fullPath = baseDir + fileName;

        // Replace backslashes with forward slashes (more compatible with URLs)
        String imagePath = fullPath.replace("\\", "/");
        // Correctly format the path for Windows, including the file: protocol
        imagePath = "file:///" + imagePath;

        // Debug: Print the imagePath to verify it's correctly formatted
        System.out.println(imagePath);

        // Use this path to create and set the image in its original size
        try {
            // Image constructor without width and height, preserveRatio = true, smooth = true
            Image image = new Image(imagePath, 0, 0, true, true);
            prod_image.setImage(image);
        } catch (Exception e) {
            e.printStackTrace(); // If there's an error loading the image, this will print the stack trace
        }
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
