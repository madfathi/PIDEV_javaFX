package tn.esprit.guiapplicatio.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import tn.esprit.guiapplicatio.models.Produit;
import tn.esprit.guiapplicatio.models.Seance;
import tn.esprit.guiapplicatio.test.HelloApplication;
import tn.esprit.guiapplicatio.utils.DataSource;
import tn.esprit.guiapplicatio.utils.MyDatabase;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Carte1 implements Initializable {
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



    private Seance pro;
    private int produitt_id;
    private Connection connection;
    public Carte1(){connection= MyDatabase.getInstance().getConnection();}
    public void setData(Seance pro){
        this.pro = pro;
        produitt_id = pro.getId_seance();

        prod_name.setText(pro.getType_seance());
       // prod_prix.setText( "nombre maxima"+pro.getNb_maximal());
       // pr = pro.getPrix();

        // Assuming getImageProduit() returns just the filename, e.g., "image.jpg"
        // Construct the full path
        String baseDir = "C:/Users/Lenovo/IdeaProjects/guiapplicatio/src/main/resources/tn/esprit/guiapplicatio/Photos/";
        String fileName = pro.getCategorie(); // Ensure this returns the filename like "image.jpg"
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
            Image image = new Image(imagePath,500 , 500, true, true);
            prod_image.setImage(image);
        } catch (Exception e) {
            e.printStackTrace(); // If there's an error loading the image, this will print the stack trace
        }
    }


    private int qty;

    public void setquantite(){
       /* spin=new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100,0);
        spinerqua.setValueFactory(spin);*/

    }
    private double total;
    private int pr;
    public void add(ActionEvent actionEvent){
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/tn/esprit/guiapplicatio/Reservat.fxml"));
        try {
            prod_image.getScene().setRoot(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setquantite();

    }
}
