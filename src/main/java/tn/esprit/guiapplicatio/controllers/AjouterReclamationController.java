package tn.esprit.guiapplicatio.controllers;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.Label;
import tn.esprit.guiapplicatio.models.Reclamation;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AjouterReclamationController implements Initializable {

    @FXML
    private Label fxreference;
    @FXML
    private TextArea fxnom_d;
    @FXML
    private TextArea fxprenom_d;
    @FXML
    private TextArea fxcin;
    @FXML
    private TextArea fxemail;
    @FXML
    private TextArea fxcommentaire;
    @FXML
    private TextArea fxtel;
    @FXML
    private Button ajout;
    @FXML
    private Button retour;

    public Connection cnx;
    public String sql = "";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/testproduitcat", "root", "");
        } catch (SQLException ex) {
            System.out.println("Failed to connect to database: " + ex.getMessage());
        }

        ajout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Reclamation r = new Reclamation();
                r.setNom_d(fxnom_d.getText());
                r.setPrenom_d(fxprenom_d.getText());
                int cin = 0;
                if (!fxcin.getText().isEmpty() && fxcin.getText().matches("\\d+")) {
                    cin = Integer.parseInt(fxcin.getText());
                }
                r.setCin(cin);
                r.setEmail(fxemail.getText());
                r.setCommentaire(fxcommentaire.getText());
                r.setTel(fxtel.getText());

                ajouter(r);
            }
        });

        retour.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                redirectToList();
            }
        });
    }

    public void ajouter(Reclamation r) {
        String reference = Reclamation.genererReference();
        fxreference.setText(reference);
        String nom_d = r.getNom_d();
        String prenom_d = r.getPrenom_d();
        int cin = r.getCin();
        String email = r.getEmail();
        String commentaire = r.getCommentaire();
        String tel = r.getTel();

        if (nom_d.isEmpty() && prenom_d.isEmpty() && cin == 0
                && email.isEmpty() && commentaire.isEmpty() && tel.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Tous les champs sont obligatoires!");
            alert.showAndWait();
            return;
        }

        if (reference == null || !reference.matches("^[a-zA-Z0-9]+$")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("La référence doit contenir uniquement des lettres et des chiffres!");
            alert.showAndWait();
            return;
        }

        sql = "insert into reclamation(reference, nom_d, prenom_d, cin, email, commentaire, tel, created_at, statut) values (?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, reference);
            ste.setString(2, r.getNom_d());
            ste.setString(3, r.getPrenom_d());
            ste.setInt(4, r.getCin());
            ste.setString(5, r.getEmail());
            ste.setString(6, r.getCommentaire());
            ste.setString(7, r.getTel());
            ste.setTimestamp(8, new Timestamp(System.currentTimeMillis()));
            ste.setString(9, "En cours");

            ste.executeUpdate();
            r.setReference(reference);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }


    public void redirectToList() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/tn/esprit/guiapplicatio/AfficherReclamation.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) retour.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AjouterReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addImage(MouseEvent mouseEvent) {
    }
}
