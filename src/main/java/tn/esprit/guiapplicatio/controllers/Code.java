package tn.esprit.guiapplicatio.controllers;

import tn.esprit.guiapplicatio.test.HelloApplication;
import com.google.protobuf.Message;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.sun.javafx.scene.control.Properties;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import tn.esprit.guiapplicatio.models.commande;
import tn.esprit.guiapplicatio.models.panier;
import tn.esprit.guiapplicatio.models.Produit;
import tn.esprit.guiapplicatio.services.panierservice;
//import org.example.MainFx;
import tn.esprit.guiapplicatio.utils.DataSource;
import tn.esprit.guiapplicatio.controllers.Validercommande;

import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Code {
    @FXML
    private ImageView qrcode;

    @FXML
    private Button qrcodebtn;
    public int idc,tel;
    public String nom,pre,mail,addr,pani;
    private int id;

    public String getPani() {
        return pani;
    }


    public int getIdc() {
        return idc;
    }

    public int getTel() {
        return tel;
    }

    public String getAddr() {
        return addr;
    }

    public String getMail() {
        return mail;
    }

    public String getNom() {
        return nom;
    }

    public String getPre() {
        return pre;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public void setIdc(int idc) {
        this.idc = idc;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPre(String pre) {
        this.pre = pre;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public void setPani(String pani) {
        this.pani = pani;
    }


    @FXML
    private Label user;
private commande co;
    @FXML
    private Label w;
    private Connection connection;
    public Code(){connection= DataSource.getInstance().getCnx();}
    public void setData(commande co){
        //System.out.println("wee");
        this.co=co;
      id= co.getIdc();



//image=new Image(pro.getImage(),200,99,false,true);
//prod_image.setImage(image);
    }


    public void generateQrCode(ActionEvent actionEvent) throws SQLException {

       String sql= "SELECT *FROM commande WHERE idc= 16";
        ObservableList<commande> qrcode= FXCollections.observableArrayList();

        PreparedStatement prepare = null;

            prepare = connection.prepareStatement(sql);
            ResultSet rs= prepare.executeQuery();
           commande proo;
            while (rs.next()) {
                        proo=new commande(rs.getInt("tel"),rs.getString("nom"),rs.getString("pre"), rs.getNString("mail"),rs.getString("addr"), Collections.singletonList(rs.getString("pani")));
                qrcode.add(proo);

            }


        // Vérifiez si un élément est sélectionné

        // Générez la chaîne de données pour le QR code


        //List<panier> users = papaService.recuperer();
        //List<panier> qrData=papaService.recuperer();
      String qrData = null;
        qrData= String.valueOf(qrcode);


        // Générez et affichez le QR code
        generateAndDisplayQRCode(String.valueOf((qrData)));

        // Affichez un message d'erreur ou prenez une autre action appropriée
        System.out.println("qrData");


    }

    //generate qrcode et l'afficher
    private void generateAndDisplayQRCode(String qrData) {
        try {
            // Configuration pour générer le QR code
            Map<EncodeHintType, Object> hints = new HashMap<>();
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");

            // Générer le QR code avec ZXing
            BitMatrix matrix = new MultiFormatWriter().encode(qrData, BarcodeFormat.QR_CODE, 184, 199, hints);
// Ajuster la taille de l'ImageView

            qrcode.setFitWidth(184);
            qrcode.setFitHeight(199);

            // Convertir la matrice en image JavaFX
            Image qrCodeImage = matrixToImage(matrix);

            // Afficher l'image du QR code dans l'ImageView
            qrcode.setImage(qrCodeImage);
            Alert a = new Alert(Alert.AlertType.WARNING);

            a.setTitle("Succes");
            a.setContentText("qr code generer");
            a.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Méthode pour convertir une matrice BitMatrix en image BufferedImage
    private Image matrixToImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();

        WritableImage writableImage = new WritableImage(width, height);
        PixelWriter pixelWriter = writableImage.getPixelWriter();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int pixelColor = matrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF;
                pixelWriter.setArgb(x, y, pixelColor);
            }
        }

        System.out.println("Matrice convertie en image avec succès");

        return writableImage;
    }
    @FXML
    void mama(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/tn/esprit/guiapplicatio/mail.fxml"));
        try {
            qrcodebtn.getScene().setRoot(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}

