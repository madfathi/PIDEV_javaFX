package tn.esprit.guiapplicatio.controllers;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXMLLoader;

import java.awt.*;
import java.io.*;
import java.nio.channels.FileChannel;
import java.sql.SQLException;


import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import tn.esprit.guiapplicatio.models.Reservation;
import tn.esprit.guiapplicatio.models.Seance;
import tn.esprit.guiapplicatio.services.ReservationService;
import tn.esprit.guiapplicatio.services.SeanceService;
import javafx.scene.image.Image;
import javafx.scene.Parent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import tn.esprit.guiapplicatio.test.HelloApplication;

public class AjoSeance {

    String filepath = null, filename = null, fn = null;
    @FXML
    private ListView<String> nbco;
    @FXML
    private ListView<String> typeco;
    @FXML
    private ListView<String> dureeco;
    @FXML
    private ListView<String> categorieco;
    @FXML
    private ListView<String> ides;
    @FXML
    private Label erreurLabel;
    @FXML
    private TextField suppr;
    @FXML
    private ComboBox<Integer> cobos;

    @FXML
    private Button btnImageC;
    @FXML
    private Label nerreurLabel;
    @FXML
    private Label derreurLabel;
    @FXML
    private TextField searchField;
    @FXML
    private TextField searchTextField;
    @FXML
    private ListView<?> suppra;

    @FXML

    private List<Seance> temp;

    private SeanceService css = new SeanceService();
    @FXML
    private ComboBox<String> sortCategorieBox;

    @FXML
    private TextField RechercherCategorie;
    @FXML
    private TableColumn<tn.esprit.guiapplicatio.models.Seance, Integer> id;
    @FXML
    private TableColumn<tn.esprit.guiapplicatio.models.Seance, String> ca;

    @FXML
    private TextField categorieTF;

    @FXML
    private Label dashboardText;

    @FXML
    private ImageView tfImage;
    @FXML
    private TableColumn<tn.esprit.guiapplicatio.models.Seance, Integer> du;

    @FXML
    private TextField dureeTF;

    @FXML
    private TableColumn<tn.esprit.guiapplicatio.models.Seance, Integer> nb;

    @FXML
    private TextField nbTF;

    @FXML
    private TableView<tn.esprit.guiapplicatio.models.Seance> tableview;
    FileChooser fc = new FileChooser();
    @FXML
    private TableColumn<tn.esprit.guiapplicatio.models.Seance, String> ty;
String to;
String co;
String Do;
String No;

    @FXML
    private TextField typeTF;
    @FXML
    private TableColumn<tn.esprit.guiapplicatio.models.Seance, String> editCOL;
    ObservableList<Seance> list = FXCollections.observableArrayList();
    ObservableList<Seance> datalist;
    String uploads = "C:/Users/Lenovo/IdeaProjects/guiapplicatio/img/";
    public void setTypee(String type) {
      to=type;
    }
    public void setIde(String ide) {
        id.setText(ide);
    }

    public void setCategoriee(String categorie) {
     co=categorie;
    }

    public void setDuree(String duree) {
        Do=duree;
    }

    public void setNbMaximale(String nbMaximal) {
        No=nbMaximal;

    }

    private ObservableList<Seance> originalList;
    private FilteredList<Seance> filteredList;
// Ajouter la colonne à votre TableVieweditCOL;

    public void updateTableView() throws SQLException {
        SeanceService SeanceService = new SeanceService();
        try {
            List<Seance> seances = SeanceService.recuperer();
            ObservableList<String> type = FXCollections.observableArrayList();
            ObservableList<String> categorie = FXCollections.observableArrayList();
            ObservableList<String> duree = FXCollections.observableArrayList();
            ObservableList<String> nb = FXCollections.observableArrayList();
            //ObservableList<String> iss = FXCollections.observableArrayList();

            for (Seance s : seances) {
                type.add(s.getType_seance());
                categorie.add(s.getCategorie());
                duree.add(String.valueOf(s.getDuree_seance()));
                nb.add(String.valueOf(s.getNb_maximal()));
             //   iss.add(String.valueOf(s.getId_seance()));
                System.out.println(s.getId_seance());
            }


            typeco.setItems(type);
            dureeco.setItems(duree);
            nbco.setItems(nb);
           // ides.setItems(iss);


            // Afficher les images dans la ListView
            categorieco.setCellFactory(list -> new ListCell<String>() {
                private final ImageView imageView = new ImageView();

                @Override
                protected void updateItem(String imagePath, boolean empty) {
                    super.updateItem(imagePath, empty);

                    if (empty || imagePath == null) {
                        setGraphic(null);
                    } else {

                        // Charger et afficher l'image
                        Image image = new Image("file:///" + uploads + imagePath);
                        imageView.setImage(image);
                        imageView.setFitWidth(120); // Réglez la largeur de l'image selon vos besoins
                        imageView.setFitHeight(100); // Réglez la hauteur de l'image selon vos besoins
                        setGraphic(imageView);
                    }
                }
            });
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }


        List<Seance> reListe = css.recuperer();

        for (Seance re : reListe) {
            cobos.getItems().add(re.getId_seance()); // Supposons que getId() retourne l'ID de la séance
        }
    }
    private void updateListView(int id, Seance seance) {
        typeco.getItems().set(id, seance.getType_seance());
        categorieco.getItems().set(id, seance.getCategorie());
        dureeco.getItems().set(id, String.valueOf(seance.getDuree_seance()));
        nbco.getItems().set(id, String.valueOf(seance.getNb_maximal()));
    }
    public void onSearchButtonClick(ActionEvent actionEvent) throws SQLException {
        String searchTerm = searchField.getText().toLowerCase();
        SeanceService seanceService = new SeanceService();
        List<Seance> list1 = seanceService.getAllSeances();
        originalList = FXCollections.observableArrayList(list1);
        // Créer une liste filtrée
        FilteredList<Seance> filteredList = new FilteredList<>(originalList, p -> true);
        filteredList.setPredicate(seance -> {
            if (searchTerm == null || searchTerm.isEmpty()) {
                return true; // Afficher toutes les entrées si la recherche est vide
            }
            // Filtrer en fonction du terme de recherche dans les champs pertinents
            return seance.getType_seance().toLowerCase().contains(searchTerm)
                    || seance.getCategorie().toLowerCase().contains(searchTerm)
                    || String.valueOf(seance.getDuree_seance()).contains(searchTerm)
                    || String.valueOf(seance.getNb_maximal()).contains(searchTerm);
        });

        // Mettre à jour le TableView avec la liste filtrée
        tableview.setItems(filteredList);
    }

    public void open_dashboard(MouseEvent mouseEvent) {
    }

    public void supprimerseance(ActionEvent actionEvent) {
        String idUtilisateurStr = suppr.getText();

        // Vérifier si l'ID est un entier valide
        try {
            int idUtilisateur = Integer.parseInt(idUtilisateurStr);

            // Appeler la méthode de service pour supprimer l'utilisateur par son ID
            css.supprimer(idUtilisateur);


            // Vous pouvez ajouter ici des actions supplémentaires après la suppression de l'utilisateur
        } catch (NumberFormatException e) {
            System.err.println("L'ID de l'utilisateur doit être un entier valide.");
            // Gérer l'erreur si l'utilisateur entre un ID non valide (par exemple, afficher un message d'erreur à l'utilisateur)
        } catch (SQLException e) {
            System.err.println("Erreur lors de la suppression de l'utilisateur : " + e.getMessage());
            // Gérer l'erreur (afficher un message à l'utilisateur, journaliser l'erreur, etc.)
        }

        // Afficher un message de confirmation à l'utilisateur
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Suppression");
        alert.setContentText("L'utilisateur a été supprimé avec succès.");

        alert.showAndWait();

        SeanceService SeanceService = new SeanceService();
        try {
            List<Seance> seances = SeanceService.recuperer();
            ObservableList<String> typez = FXCollections.observableArrayList();
            ObservableList<String> categoriee = FXCollections.observableArrayList();
            ObservableList<String> dureee = FXCollections.observableArrayList();
            ObservableList<String> nbe = FXCollections.observableArrayList();

            for (Seance s : seances) {
                typez.add(s.getType_seance());
                categoriee.add(s.getCategorie());
                dureee.add(String.valueOf(s.getDuree_seance()));
                nbe.add(String.valueOf(s.getNb_maximal()));
            }


            typeco.setItems(typez);
            dureeco.setItems(dureee);
            nbco.setItems(nbe);


            // Afficher les images dans la ListView
            categorieco.setCellFactory(list -> new ListCell<String>() {
                private final ImageView imageView = new ImageView();

                @Override
                protected void updateItem(String imagePath, boolean empty) {
                    super.updateItem(imagePath, empty);

                    if (empty || imagePath == null) {
                        setGraphic(null);
                    } else {

                        // Charger et afficher l'image
                        Image image = new Image("file:///" + uploads + imagePath);
                        imageView.setImage(image);
                        imageView.setFitWidth(120); // Réglez la largeur de l'image selon vos besoins
                        imageView.setFitHeight(100); // Réglez la hauteur de l'image selon vos besoins
                        setGraphic(imageView);
                    }
                }
            });
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }



    }

    public void modifiee(ActionEvent actionEvent) {



    }
    public void rowclicked(MouseEvent mouseEvent) throws IOException {
       /* FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/tn/esprit/guiapplicatio/ModifierSeance.fxml"));
        typeTF.getScene().setRoot(fxmlLoader.load());
        Seance clickedseance = tableview.getSelectionModel().getSelectedItem();
        typeTF.setText(String.valueOf(clickedseance.getType_seance()));
        categorieTF.setText(String.valueOf(clickedseance.getCategorie()));
        dureeTF.setText(String.valueOf(clickedseance.getDuree_seance()));
        nbTF.setText(String.valueOf(clickedseance.getNb_maximal()));
*/
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/tn/esprit/guiapplicatio/ModifierSeance.fxml"));
        Parent root = fxmlLoader.load();
        ModifierSeance controller = fxmlLoader.getController();
        Seance clickedSeance = tableview.getSelectionModel().getSelectedItem();
        ObservableList<Seance> Seances = tableview.getItems();
        controller.setSeances(Seances);
        controller.setId(String.valueOf(clickedSeance.getId_seance()));
        controller.setType(String.valueOf(clickedSeance.getType_seance()));
        controller.setCategorie(String.valueOf(clickedSeance.getCategorie()));
        controller.setDuree(String.valueOf(clickedSeance.getDuree_seance()));
        controller.setNbMaximal(String.valueOf(clickedSeance.getNb_maximal()));

        typeTF.getScene().setRoot(root);


    }

    public void row(MouseEvent mouseEvent) throws IOException, SQLException {
         ObservableList<Seance> seances = FXCollections.observableArrayList();
SeanceService SeanceService=new SeanceService();
        List<Seance> seancess = SeanceService.recuperer();
        ObservableList<Seance> seancesss = FXCollections.observableArrayList(seancess);
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/tn/esprit/guiapplicatio/ModifierSeance.fxml"));
        Parent root = fxmlLoader.load();
        ModifierSeance controller = fxmlLoader.getController();
        String clickedSeance = typeco.getSelectionModel().getSelectedItem();
        System.out.println("vcdvdv");
        Optional<Seance> seanceOptional = seancess.stream()
                .filter(seance -> seance.getType_seance().contains(clickedSeance))
                .findFirst();


        // Vérifier si la séance a été trouvée
        if (seanceOptional.isPresent()) {
            Seance seance = seanceOptional.get();
            controller.setSeances(seancesss);
            // Mettre à jour les autres attributs dans le contrôleur ModifierSeance
            controller.setId(String.valueOf(seance.getId_seance()));
            controller.setType(seance.getType_seance());
            controller.setCategorie(seance.getCategorie());
            controller.setDuree(String.valueOf(seance.getDuree_seance()));
            controller.setNbMaximal(String.valueOf(seance.getNb_maximal()));

            // Remplacer la scène actuelle par la scène de modification
            typeco.getScene().setRoot(root);
        } else {
            System.out.println("Aucune séance trouvée avec l'ID : " );
        }

        typeco.getScene().setRoot(root);





    }

    public void row1(MouseEvent mouseEvent) throws SQLException, IOException {
        ObservableList<Seance> seances = FXCollections.observableArrayList();
        SeanceService SeanceService=new SeanceService();
        List<Seance> seancess = SeanceService.recuperer();
        ObservableList<Seance> seancesss = FXCollections.observableArrayList(seancess);
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/tn/esprit/guiapplicatio/ModifierSeance.fxml"));
        Parent root = fxmlLoader.load();
        ModifierSeance controller = fxmlLoader.getController();
        String clickedSeance = typeco.getSelectionModel().getSelectedItem();
        System.out.println("vcdvdv");
        Optional<Seance> seanceOptional = seancess.stream()
                .filter(seance -> seance.getType_seance().contains(clickedSeance))
                .findFirst();


        // Vérifier si la séance a été trouvée
        if (seanceOptional.isPresent()) {
            Seance seance = seanceOptional.get();
            controller.setSeances(seancesss);
            // Mettre à jour les autres attributs dans le contrôleur ModifierSeance
            controller.setId(String.valueOf(seance.getId_seance()));
            controller.setType(seance.getType_seance());
            controller.setCategorie(seance.getCategorie());
            controller.setDuree(String.valueOf(seance.getDuree_seance()));
            controller.setNbMaximal(String.valueOf(seance.getNb_maximal()));

            // Remplacer la scène actuelle par la scène de modification
            typeco.getScene().setRoot(root);
        } else {
            System.out.println("Aucune séance trouvée avec l'ID : " );
        }

        typeco.getScene().setRoot(root);


    }

    public void refreshing(ActionEvent actionEvent) {
        categorieco.setCellFactory(list -> new ListCell<String>() {
            private final ImageView imageView = new ImageView();

            @Override
            protected void updateItem(String imagePath, boolean empty) {
                super.updateItem(imagePath, empty);

                if (empty || imagePath == null) {
                    setGraphic(null);
                } else {

                    // Charger et afficher l'image
                    Image image = new Image("file:///" + uploads + imagePath);
                    imageView.setImage(image);
                    imageView.setFitWidth(120); // Réglez la largeur de l'image selon vos besoins
                    imageView.setFitHeight(100); // Réglez la hauteur de l'image selon vos besoins
                    setGraphic(imageView);
                }
            }
        });

    }

    public void ge_re(ActionEvent actionEvent) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/tn/esprit/guiapplicatio/Reservat.fxml"));
        typeco.getScene().setRoot(fxmlLoader.load());



    }


    private class ButtonCell extends TableCell<Seance, Boolean> {
        final Button cellButton = new Button("Supprimer");

        ButtonCell() {
            cellButton.setOnAction(event -> {
                Seance seance = getTableView().getItems().get(getIndex());
                // Supprimer la réservation de votre liste

                try {
                    css.supprimer(seance.getId_seance());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                tableview.getItems().remove(seance);
                // Si vous souhaitez également supprimer la réservation de la base de données, utilisez le service approprié ici
                // cs2.supprimer(reservation.getId_reservation());
            });
        }


        // Affiche le bouton uniquement pour les lignes non vides
        @Override
        protected void updateItem(Boolean item, boolean empty) {
            super.updateItem(item, empty);
            if (empty) {
                setGraphic(null);
            } else {
                setGraphic(cellButton);
            }
        }
    }

    @FXML
    public void ajoSeance(ActionEvent actionEvent) throws SQLException {

        String type = typeTF.getText();
        String duree = dureeTF.getText();
        String nbMaximal = nbTF.getText();
        SeanceService cs = new SeanceService();
        tn.esprit.guiapplicatio.models.Seance co = new tn.esprit.guiapplicatio.models.Seance();
        co.setType_seance(typeTF.getText());
        co.setCategorie(filename);
        co.setDuree_seance(Integer.parseInt(dureeTF.getText()));
        co.setNb_maximal(Integer.parseInt(nbTF.getText()));

        erreurLabel.setText(null);
        derreurLabel.setText(null);
        nerreurLabel.setText(null);

        if (type.isEmpty() || duree.isEmpty() || nbMaximal.isEmpty() ||
                !typeTF.getText().matches("^[a-zA-Z]+$") ||
                Integer.parseInt(duree) < 2 || Integer.parseInt(duree) > 4 ||
                Integer.parseInt(nbMaximal) < 1 || Integer.parseInt(nbMaximal) > 10) {


            if (type.isEmpty()) {
                erreurLabel.setText("Le type ne doit pas être vide");
                erreurLabel.setStyle("-fx-text-fill: red;");
            }

            if (duree.isEmpty()) {
                derreurLabel.setText("La durée ne doit pas être vide");
                derreurLabel.setStyle("-fx-text-fill: red;");
            }

            if (nbMaximal.isEmpty()) {
                nerreurLabel.setText("Le nombre maximal ne doit pas être vide");
                nerreurLabel.setStyle("-fx-text-fill: red;");
            }

            if (!typeTF.getText().matches("^[a-zA-Z]+$")) {
                erreurLabel.setText("Le type doit contenir uniquement des lettres.");
                erreurLabel.setStyle("-fx-text-fill: red;");
            }

            if (Integer.parseInt(duree) < 2 || Integer.parseInt(duree) > 4) {
                derreurLabel.setText("La durée doit être entre 2 et 4.");
                derreurLabel.setStyle("-fx-text-fill: red;");
            }

            if (Integer.parseInt(nbMaximal) < 1 || Integer.parseInt(nbMaximal) > 10) {
                nerreurLabel.setText("Le nombre maximal doit être entre 1 et 10.");
                nerreurLabel.setStyle("-fx-text-fill: red;");
            }

        } else {
            try {
                cs.ajouter(co);

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Success");
                alert.setContentText("client ajoutée");
                alert.showAndWait();
                updateTableView();
            } catch (SQLException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }


        }

        SeanceService cs2 = new SeanceService();
        try {
            List<tn.esprit.guiapplicatio.models.Seance> seances = cs2.recuperer();
            ObservableList<tn.esprit.guiapplicatio.models.Seance> observableList = FXCollections.observableList(seances);
            tableview.setItems(observableList);
            id.setCellValueFactory(new PropertyValueFactory<>("id_seance"));
            ty.setCellValueFactory(new PropertyValueFactory<>("type_seance"));
            ca.setCellValueFactory(new PropertyValueFactory<>("categorie"));
            du.setCellValueFactory(new PropertyValueFactory<>("duree_seance"));
            nb.setCellValueFactory(new PropertyValueFactory<>("nb_maximal"));
        } catch (SQLException e) {
            System.err.println(e.getMessage());

        }
        ObservableList<Seance> updatedList;

// Initialisation de la liste observable
        updatedList = FXCollections.observableArrayList();

// Récupération des données depuis SeanceService
        List<Seance> seancesList = css.recuperer();

// Ajout des données à la liste observable
        updatedList.addAll(seancesList);
        tableview.setItems(updatedList);
        ObservableList<String> typeSeanceList = updatedList.stream()
                .map(Seance::getType_seance)
                .collect(Collectors.toCollection(FXCollections::observableArrayList));

        ObservableList<String>   categorieSeanceList   = updatedList.stream()
                .map(Seance::getCategorie)
                .collect(Collectors.toCollection(FXCollections::observableArrayList));


        ObservableList<String> duree_Senace = updatedList.stream()
                .map(s -> String.valueOf(s.getDuree_seance()))
                .collect(Collectors.toCollection(FXCollections::observableArrayList));

        ObservableList<String> nb_maximal = updatedList.stream()
                .map(s -> String.valueOf(s.getDuree_seance()))
                .collect(Collectors.toCollection(FXCollections::observableArrayList));

        typeco.setItems(typeSeanceList);
        categorieco.setItems(categorieSeanceList);
        dureeco.setItems(duree_Senace);
        nbco.setItems(nb_maximal);



    /*    SeanceService SeanceService = new SeanceService();
        try {
            List<Seance> seances = SeanceService.recuperer();
            ObservableList<String> typez = FXCollections.observableArrayList();
            ObservableList<String> categoriee = FXCollections.observableArrayList();
            ObservableList<String> dureee = FXCollections.observableArrayList();
            ObservableList<String> nbe = FXCollections.observableArrayList();

            for (Seance s : seances) {
                typez.add(s.getType_seance());
                categoriee.add(s.getCategorie());
                dureee.add(String.valueOf(s.getDuree_seance()));
                nbe.add(String.valueOf(s.getNb_maximal()));
            }


            typeco.setItems(typez);
            dureeco.setItems(dureee);
            nbco.setItems(nbe);


            // Afficher les images dans la ListView
            categorieco.setCellFactory(list -> new ListCell<String>() {
                private final ImageView imageView = new ImageView();

                @Override
                protected void updateItem(String imagePath, boolean empty) {
                    super.updateItem(imagePath, empty);

                    if (empty || imagePath == null) {
                        setGraphic(null);
                    } else {

                        // Charger et afficher l'image
                        Image image = new Image("file:///" + uploads + imagePath);
                        imageView.setImage(image);
                        imageView.setFitWidth(120); // Réglez la largeur de l'image selon vos besoins
                        imageView.setFitHeight(100); // Réglez la hauteur de l'image selon vos besoins
                        setGraphic(imageView);
                    }
                }
            });
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
*/



    }


    @FXML
    void initialize() throws SQLException {
      /*  ides.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                int selectedIndex = ides.getSelectionModel().getSelectedIndex();
                updateListView(selectedIndex, newValue);
            }
        });*/



        //SeanceService SeanceService1 = new SeanceService();

        List<Seance> reListe = css.recuperer();

        for (Seance re : reListe) {
            cobos.getItems().add(re.getId_seance()); // Supposons que getId() retourne l'ID de la séance
        }








        updateTableView();
        SeanceService SeanceService = new SeanceService();
        try {
            List<Seance> seances = SeanceService.recuperer();
            ObservableList<String> type = FXCollections.observableArrayList();
            ObservableList<String> categorie = FXCollections.observableArrayList();
            ObservableList<String> duree = FXCollections.observableArrayList();
            ObservableList<String> nb = FXCollections.observableArrayList();
         //   ObservableList<String> iss = FXCollections.observableArrayList();

            for (Seance s : seances) {
                type.add(s.getType_seance());
                categorie.add(s.getCategorie());
                duree.add(String.valueOf(s.getDuree_seance()));
                nb.add(String.valueOf(s.getNb_maximal()));
           //     iss.add(String.valueOf(s.getId_seance()));

            }


            typeco.setItems(type);
            dureeco.setItems(duree);
            nbco.setItems(nb);
            //ides.setItems(iss);


            // Afficher les images dans la ListView
            categorieco.setCellFactory(list -> new ListCell<String>() {
                private final ImageView imageView = new ImageView();

                @Override
                protected void updateItem(String imagePath, boolean empty) {
                    super.updateItem(imagePath, empty);

                    if (empty || imagePath == null) {
                        setGraphic(null);
                    } else {

                        // Charger et afficher l'image
                        Image image = new Image("file:///" + uploads + imagePath);
                        imageView.setImage(image);
                        imageView.setFitWidth(120); // Réglez la largeur de l'image selon vos besoins
                        imageView.setFitHeight(100); // Réglez la hauteur de l'image selon vos besoins
                        setGraphic(imageView);
                    }
                }
            });
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
         ObservableList<Seance> updatedList;

// Initialisation de la liste observable
        updatedList = FXCollections.observableArrayList();

// Récupération des données depuis SeanceService
        List<Seance> seancesList = css.recuperer();

// Ajout des données à la liste observable
        updatedList.addAll(seancesList);
        tableview.setItems(updatedList);
        ObservableList<String> typeSeanceList = updatedList.stream()
                .map(Seance::getType_seance)
                .collect(Collectors.toCollection(FXCollections::observableArrayList));

        ObservableList<String>   categorieSeanceList   = updatedList.stream()
                .map(Seance::getCategorie)
                .collect(Collectors.toCollection(FXCollections::observableArrayList));


        ObservableList<String> duree_Senace = updatedList.stream()
                .map(s -> String.valueOf(s.getDuree_seance()))
                .collect(Collectors.toCollection(FXCollections::observableArrayList));

        ObservableList<String> nb_maximal = updatedList.stream()
                .map(s -> String.valueOf(s.getDuree_seance()))
                .collect(Collectors.toCollection(FXCollections::observableArrayList));

        typeco.setItems(typeSeanceList);
        categorieco.setItems(categorieSeanceList);
        dureeco.setItems(duree_Senace);
        nbco.setItems(nb_maximal);

        ObservableList<String> list = FXCollections.observableArrayList("Trier par Nom ↑", "Trier par Nom ↓");

        sortCategorieBox.setItems(list);

        SeanceService cs2 = new SeanceService();
        editCOL = new TableColumn<>("Edit Column");
        List<tn.esprit.guiapplicatio.models.Seance> seances = cs2.recuperer();
        ObservableList<tn.esprit.guiapplicatio.models.Seance> observableList = FXCollections.observableList(seances);
        // tableview.setItems(observableList);
        id.setCellValueFactory(new PropertyValueFactory<>("id_seance"));
        ty.setCellValueFactory(new PropertyValueFactory<>("type_seance"));
        du.setCellValueFactory(new PropertyValueFactory<>("duree_seance"));
        nb.setCellValueFactory(new PropertyValueFactory<>("nb_maximal"));
        ca.setCellFactory(column -> new TableCell<Seance, String>() {
            private final ImageView imageView = new ImageView();

            @Override
            protected void updateItem(String imagePath, boolean empty) {
                super.updateItem(imagePath, empty);

                if (empty || imagePath == null) {
                    setGraphic(null);
                } else {

                    // Charger et afficher l'image
                    Image image = new Image("file:///" + uploads + imagePath);
                    imageView.setImage(image);
                    imageView.setFitWidth(120); // Réglez la largeur de l'image selon vos besoins
                    imageView.setFitHeight(100); // Réglez la hauteur de l'image selon vos besoins
                    setGraphic(imageView);
                }
            }
        });
        ca.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        ObservableList<Seance> list1 = css.readCategorie();
        tableview.setItems(list1);
        editCOL.setCellFactory(column -> new TableCell<Seance, String>() {
            private final ImageView imageView = new ImageView();

            @Override
            protected void updateItem(String imagePath, boolean empty) {
                super.updateItem(imagePath, empty);

                if (empty || imagePath == null) {
                    setGraphic(null);
                } else {
                    // Charger et afficher l'image
                    Image image = new Image("file:///" + uploads + imagePath);
                    imageView.setImage(image);
                    imageView.setFitWidth(120); // Réglez la largeur de l'image selon vos besoins
                    imageView.setFitHeight(100); // Réglez la hauteur de l'image selon vos besoins
                    setGraphic(imageView);
                }
            }
        });
        editCOL.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        ObservableList<Seance> list2 = css.readCategorie();
        tableview.setItems(list2);


        editCOL.setCellValueFactory(cellData -> {
            ImageView imageView = new ImageView(new Image("file:///C:/Users/Lenovo/IdeaProjects/guiapplicatio/src/assets/img/BATMAN.png"));
            imageView.setFitWidth(50);
            imageView.setFitHeight(50);
            return new SimpleObjectProperty<>(imageView).asString();
        });
        TableColumn<Seance, Boolean> btnCol = new TableColumn<>("Action");
        btnCol.setSortable(false); // Désactive le tri sur cette colonne

        // Définissez la cellule de bouton personnalisée pour cette colonne
        btnCol.setCellFactory(col -> new AjoSeance.ButtonCell());

        // Ajoutez la colonne de bouton à votre TableView
        tableview.getColumns().add(btnCol);
        tableview.setItems(observableList);


    }


    public void refresh(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/tn/esprit/guiapplicatio/ModifierSeance.fxml"));
typeTF.getScene().setRoot(fxmlLoader.load());
    }




    @FXML
    public void addEmployeeSearch() {
        /*id.setCellValueFactory(new PropertyValueFactory<>("id_seance"));
        ty.setCellValueFactory(new PropertyValueFactory<>("type_seance"));
        ca.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        du.setCellValueFactory(new PropertyValueFactory<>("duree_seance"));
        nb.setCellValueFactory(new PropertyValueFactory<>("nb_maximal") );

        FilteredList<Seance> filter = new FilteredList<>(list, ev -> true);

        RechercherCategorie.textProperty().addListener((observable, oldValue, newValue) -> {
            filter.setPredicate(t -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (t.getType_seance().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else {
                    return false;
                }
            });
        });

        SortedList<Seance> sort = new SortedList<>(filter);
        sort.comparatorProperty().bind(tableview.comparatorProperty());
        tableview.setItems(sort);
*/

    }



    public void sortCategorie(ActionEvent actionEvent) {
        String selected = sortCategorieBox.getSelectionModel().getSelectedItem();
        if (selected.equals("Trier par Nom ↑")) {
            temp = css.sortCategorieAsc();

        } else if (selected.equals("Trier par Nom ↓")) {
            temp = css.sortCategorieDesc();

        }
        // Mettez à jour la liste observable utilisée par votre TableView (par exemple, 'list')
        ObservableList<Seance> updatedList = FXCollections.observableArrayList(temp);

        // Mettre à jour la TableView
        tableview.setItems(updatedList);
        ObservableList<String> typeSeanceList = updatedList.stream()
                .map(Seance::getType_seance)
                .collect(Collectors.toCollection(FXCollections::observableArrayList));

        ObservableList<String>   categorieSeanceList   = updatedList.stream()
                .map(Seance::getCategorie)
                .collect(Collectors.toCollection(FXCollections::observableArrayList));


        ObservableList<String> duree_Senace = updatedList.stream()
                .map(s -> String.valueOf(s.getDuree_seance()))
                .collect(Collectors.toCollection(FXCollections::observableArrayList));

        ObservableList<String> nb_maximal = updatedList.stream()
                .map(s -> String.valueOf(s.getDuree_seance()))
                .collect(Collectors.toCollection(FXCollections::observableArrayList));

        typeco.setItems(typeSeanceList);
        categorieco.setItems(categorieSeanceList);
        dureeco.setItems(duree_Senace);
        nbco.setItems(nb_maximal);

    }

    public void btnImageC(ActionEvent actionEvent) throws IOException {
        File file = fc.showOpenDialog(null);
        // Shows a new file open dialog.
        if (file != null) {
            // URI that represents this abstract pathname
            tfImage.setImage(new Image(file.toURI().toString()));

            filename = file.getName();
            filepath = file.getAbsolutePath();

            fn = filename;

            FileChannel source = new FileInputStream(filepath).getChannel();
            FileChannel dest = new FileOutputStream(uploads + filename).getChannel();
            dest.transferFrom(source, 0, source.size());
        } else {
            System.out.println("Fichier invalide!");
        }
    }

    public void supprimer(ActionEvent actionEvent) throws SQLException {

        SeanceService s=new SeanceService();
        Integer selectedId = cobos.getSelectionModel().getSelectedItem();
        s.supprimer(selectedId);
        cobos.getItems().remove(selectedId);

        SeanceService SeanceService = new SeanceService();
        try {
            List<Seance> seances = SeanceService.recuperer();
            ObservableList<String> type = FXCollections.observableArrayList();
            ObservableList<String> categorie = FXCollections.observableArrayList();
            ObservableList<String> duree = FXCollections.observableArrayList();
            ObservableList<String> nb = FXCollections.observableArrayList();
            //   ObservableList<String> iss = FXCollections.observableArrayList();

            for (Seance sa : seances) {
                type.add(sa.getType_seance());
                categorie.add(sa.getCategorie());
                duree.add(String.valueOf(sa.getDuree_seance()));
                nb.add(String.valueOf(sa.getNb_maximal()));
                //     iss.add(String.valueOf(s.getId_seance()));

            }


            typeco.setItems(type);
            dureeco.setItems(duree);
            nbco.setItems(nb);
            //ides.setItems(iss);


            // Afficher les images dans la ListView
            categorieco.setCellFactory(list -> new ListCell<String>() {
                private final ImageView imageView = new ImageView();

                @Override
                protected void updateItem(String imagePath, boolean empty) {
                    super.updateItem(imagePath, empty);

                    if (empty || imagePath == null) {
                        setGraphic(null);
                    } else {

                        // Charger et afficher l'image
                        Image image = new Image("file:///" + uploads + imagePath);
                        imageView.setImage(image);
                        imageView.setFitWidth(120); // Réglez la largeur de l'image selon vos besoins
                        imageView.setFitHeight(100); // Réglez la hauteur de l'image selon vos besoins
                        setGraphic(imageView);
                    }
                }
            });
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }





        /*SeanceService caa=new SeanceService();
        String idText = suppr.getText();
        int id1 = Integer.parseInt(idText);
        try {

            if (caa.idEsists(id1)) {


                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Success");
                alert.setContentText("client ajoutée");
                alert.showAndWait();
                caa.supprimer(id1);
            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.ERROR) ;
                alert.setTitle("Erreur");

                alert.showAndWait();


            }
        } catch (SQLException e) {


        }

        SeanceService cs2 = new SeanceService();
        try {
            List<tn.esprit.guiapplicatio.models.Seance> seances= cs2.recuperer();
            ObservableList<tn.esprit.guiapplicatio.models.Seance> observableList = FXCollections.observableList(seances);
            tableview.setItems(observableList);
            id.setCellValueFactory(new PropertyValueFactory<>("id_seance"));
            ty.setCellValueFactory(new PropertyValueFactory<>("type_seance"));
            ca.setCellValueFactory(new PropertyValueFactory<>("categorie"));
            du.setCellValueFactory(new PropertyValueFactory<>("duree_seance"));
            nb.setCellValueFactory(new PropertyValueFactory<>("nb_maximal") );
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR) ;
            alert.setTitle("Erreur");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            System.err.println(e.getMessage());

        }

*/
        List<Seance> reListe = css.recuperer();

        for (Seance re : reListe) {
            cobos.getItems().add(re.getId_seance()); // Supposons que getId() retourne l'ID de la séance
        }
    }

    public void pdf(ActionEvent actionEvent) {
        ObservableList<Seance> data = tableview.getItems();

        try {
            // Créez un nouveau document PDF
            PDDocument document = new PDDocument();

            // Créez une page dans le document
            PDPage page = new PDPage();
            document.addPage(page);

            // Obtenez le contenu de la page
            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            // Écrivez du texte dans le document
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
            contentStream.beginText();
            contentStream.newLineAtOffset(100, 700);


           for (Seance categorie : data) {
// Ajouter l'image
                String imagePath = uploads + categorie.getCategorie();
                PDImageXObject pdImage = PDImageXObject.createFromFile(imagePath, document);

                String ligne = "ID : " + categorie.getId_seance() + "     Nom : " + categorie.getType_seance()+categorie.getDuree_seance()+categorie.getNb_maximal();
                contentStream.showText(ligne);

                contentStream.newLine();;
                contentStream.newLineAtOffset(0, -15);


            }

            contentStream.endText();

            // Fermez le contenu de la page
            contentStream.close();

            String outputPath = "C:/Users/Lenovo/IdeaProjects/guiapplicatio/src/PDF/seance.pdf";
            File file = new File(outputPath);
            document.save(file);

            // Fermez le document
            document.close();

            System.out.println("Le PDF a été généré avec succès.");
            Desktop.getDesktop().open(file);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}





