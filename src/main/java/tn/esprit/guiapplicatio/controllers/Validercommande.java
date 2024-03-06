package tn.esprit.guiapplicatio.controllers;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import tn.esprit.guiapplicatio.test.HelloApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import  tn.esprit.guiapplicatio.models.commande;
import tn.esprit.guiapplicatio.models.panier;
import tn.esprit.guiapplicatio.services.commandeservice;
import tn.esprit.guiapplicatio.services.panierservice;
//import org.example.MainFx;
import tn.esprit.guiapplicatio.utils.DataSource;

import java.io.IOException;
import java.sql.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Validercommande {
    @FXML
    private Button btn;
    @FXML
    private ImageView qrcode;
    @FXML
    private ListView<String> pp;

    @FXML
    private ListView<String> pr;
    @FXML
    private ListView<String> qu;
    @FXML
    private TextField addr;

    @FXML
    private TextField mail;

    @FXML
    private TextField nom;

    @FXML
    private TextField pre;

    @FXML
    private TableView<panier> taborder;

    @FXML
    private TableColumn<?, ?> tabp;

    @FXML
    private TableColumn<?, ?> tabpro;

    @FXML
    private TableColumn<?, ?> tabq;

    @FXML
    private TextField tel;

    @FXML
    private Label tot;
    private int tota;
    private panier pro;
    private Connection connection;


    public Validercommande() {
        connection = DataSource.getInstance().getCnx();
    }


    @FXML
    void ajouter(ActionEvent event) throws SQLException {
        panierservice papaService = null;
        papaService = new panierservice();

        List<panier> users = papaService.recuperer();
        ObservableList<String> nomm = FXCollections.observableArrayList();
        ObservableList<String> we = FXCollections.observableArrayList();
        for (panier user : users) {
            nomm.add(String.valueOf(user.getPt()));
            we.add(user.getNomp());
        }
        /*String paniValue = ""; // Initialize with an empty string
        int prixValue = 0;
            String req = "SELECT nomp, pt FROM panier";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(req);
            if (rs.next()) {
                paniValue = rs.getString("nomp");
                prixValue = rs.getInt("pt");
            }*/

        // Exit the method if no records found in the "panier" table

        // Input validation
        if (nom.getText().isEmpty() || pre.getText().isEmpty() || mail.getText().isEmpty() || addr.getText().isEmpty() || tel.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText("Veuillez remplir tous les champs.");
            alert.showAndWait();
            return; // Exit the method early if any field is empty
        }

        // Validate email format
        if (!mail.getText().contains("@")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText("Veuillez entrer une adresse e-mail valide.");
            alert.showAndWait();
            return; // Exit the method early if email format is invalid
        }

        // Validate phone number format
        try {
            Integer.parseInt(tel.getText());
        } catch (NumberFormatException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText("Le numéro de téléphone doit être un nombre entier.");
            alert.showAndWait();
            return; // Exit the method early if phone number format is invalid
        }

        commandeservice cs = new commandeservice();
        commande co = new commande();
        co.setNom(nom.getText());
        co.setPre(pre.getText());
        co.setMail(mail.getText());
        co.setAddr(addr.getText());
        co.setPani(Collections.singletonList(we + "" + nomm + "" + tota));

        co.setTel(Integer.parseInt(tel.getText()));

        try {
            cs.ajouter(co);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Success");
            alert.setContentText(" succès");
            alert.showAndWait();
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText("Une erreur : " + ex.getMessage());
            alert.showAndWait();
        }
  /*      FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/tn/esprit/guiapplicatio/code.fxml"));
        try {
            tel.getScene().setRoot(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
*/
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
    void papa(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/tn/esprit/guiapplicatio/Panier.fxml"));
        try {
            nom.getScene().setRoot(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void menutotal() throws SQLException {
        String req = "SELECT SUM(pt) FROM panier";
        PreparedStatement pre = connection.prepareStatement(req);
        ResultSet res = pre.executeQuery();
        if (res.next()) {
            tota = res.getInt("SUM(pt)");

        }
        tot.setText(tota + "" + "DT");

    }

    @FXML
    void initialize() throws SQLException {








        panierservice co = null;
        co = new panierservice();
                List<panier> coo = co.recuperer();
                ObservableList<String> prr = FXCollections.observableArrayList();
                ObservableList<String> quu = FXCollections.observableArrayList();
                ObservableList<String> ppp = FXCollections.observableArrayList();
                for (panier cot : coo) {
                    prr.add(cot.getNomp());
                    quu.add(String.valueOf(cot.getQuantite()));
                    ppp.add(String.valueOf(cot.getPt()));





    }
        pr.setItems(prr);
        qu.setItems(quu);
        pp.setItems(ppp);
        menutotal();
    }
}
