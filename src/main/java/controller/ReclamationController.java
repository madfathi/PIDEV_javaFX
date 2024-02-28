package controller;

import com.mysql.cj.xdevapi.Statement;
import entities.Reclamation;
import javafx.scene.control.*;
import javafx.util.Callback;
import service.ReclamationService;
import utils.DataSource;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
public class ReclamationController implements Initializable{
    @FXML
    private Button reponse;
    @FXML
    private Button approuver;
    @FXML
    private Button refuser;
    @FXML
    private ListView<Reclamation> lvReclamation;
    @FXML
    private Pane paneReclamations;
    @FXML
    private Button reponsebtn;

    public Connection cnx;
    public Statement stm;
    String sql = "";
    public int idSelected = -1;
    @FXML
    private Button refresh;
    @FXML
    private Button GestionRecla;
    @FXML
    private ImageView image;
    @FXML
    private Button tric;
    @FXML
    private Button trid;
    @FXML
    private Button handleStatButton;

    ObservableList<Reclamation> data = FXCollections.observableArrayList();

    @FXML
    private TextField tfrecherche;
    ReclamationService rs = new ReclamationService();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // Initialize your database connection here
            cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/testproduitcat");
        } catch (SQLException ex) {
            System.out.println("Failed to connect to database: " + ex.getMessage());
        }

        approuver.setOnAction((ActionEvent event) -> {
            approuver(event);
        });

        refuser.setOnAction((ActionEvent event) -> {
            refuser(event);
        });


//        reponsebtn.setOnAction((ActionEvent event) -> {
//            reponsebtn();
//        });

        // Appel de la méthode ShowListe() pour afficher la liste des réclamations
        ShowListe();

        //recherche
        tfrecherche.textProperty().addListener((observable, oldValue, newValue) -> {
            rechercherReclamations(newValue);
        });

    }

    public ObservableList<Reclamation> getReclamationList() {
        cnx = DataSource.getInstance().getCnx();
        ObservableList<Reclamation> ReclamationList = FXCollections.observableArrayList();
        try {
            String query2 = "SELECT * from reclamation";
            PreparedStatement smt = cnx.prepareStatement(query2);
            ResultSet rs = smt.executeQuery();
            while (rs.next()) {
                Reclamation r = new Reclamation(rs.getInt("id"), rs.getString("reference"), rs.getString("nom_d"), rs.getString("prenom_d"), rs.getInt("cin"), rs.getString("email"), rs.getString("commentaire"), rs.getDate("created_at"), rs.getString("statut"), rs.getString("file"), rs.getString("tel"));
                ReclamationList.add(r);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return ReclamationList;
    }

    public void ShowListe() {
        ObservableList<Reclamation> list = getReclamationList();
        lvReclamation.setItems(list);

        // Définir la cellFactory pour le ListView
        lvReclamation.setCellFactory(new Callback<ListView<Reclamation>, ListCell<Reclamation>>() {
            @Override
            public ListCell<Reclamation> call(ListView<Reclamation> param) {
                return new ListCell<Reclamation>() {
                    @Override
                    protected void updateItem(Reclamation item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            // Définir le texte à afficher pour chaque attribut de l'objet Reclamation
                            setText("Référence: " + item.getReference() + "\n"
                                    + "Nom: " + item.getNom_d() + "\n"
                                    + "Prénom: " + item.getPrenom_d() + "\n"
                                    + "CIN: " + item.getCin() + "\n"
                                    + "Email: " + item.getEmail() + "\n"
                                    + "Commentaire: " + item.getCommentaire() + "\n"
                                    + "Créé le: " + item.getCreated_at() + "\n"
                                    + "Statut: " + item.getStatut() + "\n"
                                    + "Fichier: " + item.getFile() + "\n"
                                    + "Téléphone: " + item.getTel() + "\n");

                        } else {
                            setText(null);
                        }
                    }
                };
            }
        });
    }


    @FXML
    private void refresh(ActionEvent event) {
        ShowListe();
    }



    @FXML
    private void approuver(ActionEvent event) {
        Reclamation selectedReclamation = lvReclamation.getSelectionModel().getSelectedItem();
        if (selectedReclamation == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Avertissement");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner une réclamation à approuver.");
            alert.showAndWait();
            return;
        }
        int selectedReclamationId = selectedReclamation.getId();
        ReclamationService reclamationService = new ReclamationService();
        selectedReclamation.setStatut("Approuvée");
        try {
            reclamationService.modifier(selectedReclamationId, selectedReclamation); // Appel à la méthode modifier
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("La réclamation a été approuvée avec succès.");
            alert.showAndWait();
            ShowListe();
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Une erreur est survenue lors de la modification de la réclamation.");
            alert.showAndWait();
        }
    }

    @FXML
    private void refuser(ActionEvent event) {
        Reclamation selectedReclamation = lvReclamation.getSelectionModel().getSelectedItem();
        if (selectedReclamation == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Avertissement");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner une réclamation à refuser.");
            alert.showAndWait();
            return;
        }
        int selectedReclamationId = selectedReclamation.getId();
        ReclamationService reclamationService = new ReclamationService();
        selectedReclamation.setStatut("Refusée");
        try {
            reclamationService.modifier(selectedReclamationId, selectedReclamation); // Appel à la méthode modifier
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("La réclamation a été refusée avec succès.");
            alert.showAndWait();
            ShowListe();
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Une erreur est survenue lors de la modification de la réclamation.");
            alert.showAndWait();
        }
    }




        public void rechercherReclamations(String recherche) {
            ObservableList<Reclamation> list = getReclamationList();

            if (recherche != null && !recherche.isEmpty()) {
                Predicate<Reclamation> recla = reclamation -> {
                    String reference = reclamation.getReference();
                    String nom = reclamation.getNom_d();
                    String prenom = reclamation.getPrenom_d();
                    String statut = reclamation.getStatut();

                    return reference.contains(recherche)
                            || nom.contains(recherche)
                            || prenom.contains(recherche)
                            || statut.contains(recherche);
                };

                list = list.filtered(recla);
            }

            lvReclamation.setItems(list);
        }

//    private void reponsebtn() {
//        Parent root;
//        try {
//            root = FXMLLoader.load(getClass().getResource("/tn/esprit/gui/Reponse.fxml"));
//            Scene c = new Scene(root);
//            Stage stage = (Stage) reponsebtn.getScene().getWindow();
//            stage.setScene(c);
//        } catch (IOException ex) {
//            Logger.getLogger(ReclamationsController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

    @FXML
    private void returnHome(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ProduitListView.fxml"));
        Parent root = loader.load();

        // Get the current stage and set the new scene
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
//    @FXML
//    void AfficherGestionReclamation(ActionEvent event) throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherReclamation.fxml"));
//        Parent root = loader.load();
//
//        // Get the current stage and set the new scene
//        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        Scene scene = new Scene(root);
//        stage.setScene(scene);
//        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/styles.css")).toExternalForm());
//        stage.show();
//
//    }

}
